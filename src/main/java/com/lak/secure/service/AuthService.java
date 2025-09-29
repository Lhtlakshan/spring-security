package com.lak.secure.service;

import com.lak.secure.dto.LoginRequestDto;
import com.lak.secure.dto.LoginResponseDto;
import com.lak.secure.dto.RegisterRequestDto;
import com.lak.secure.dto.RegisterResponseDto;
import com.lak.secure.entity.UserEntity;
import com.lak.secure.enums.UserRole;
import com.lak.secure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    public List<UserEntity> getAllUsers(){
        return userRepository.findAll();
    }

    public RegisterResponseDto registerUser(RegisterRequestDto registerRequest){

        if(this.isUserEnable(registerRequest.getEmail())){
            return new RegisterResponseDto("User already exist","cannot register using this email");
        }

        if(registerRequest.getUserRole() == UserRole.ADMIN){
            return new RegisterResponseDto("Admin user can only be registered by admin","Admin user cannot be registered");
        }

        UserEntity user = new UserEntity(
                registerRequest.getName(),
                registerRequest.getEmail(),
                passwordEncoder.encode(registerRequest.getPassword()),
                registerRequest.getUserRole()
        );
        UserEntity userEntity = userRepository.save(user);

        if(userEntity.getId() == null) new RegisterResponseDto(null, "System error");

        return new RegisterResponseDto(String.format("User registered %s", userEntity.getId()),null);
    }

    public LoginResponseDto login(LoginRequestDto loginData){
//        if(isUserEnable(loginData.getUsername())){
//            return new LoginResponseDto(null, LocalDateTime.now(), "User not found", "Login error");
//        }
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginData.getUsername(), loginData.getPassword()));
        }catch (Exception ex){
            return new LoginResponseDto(null, LocalDateTime.now(), "User not found", "Login error");
        }

        //add to the payload data for jwt token (claims)
        Map<String,Object> claims = new HashMap<>();
        claims.put("role","User");
        claims.put("email", "company@gmail.com");

        String token = jwtService.generateToken(loginData.getUsername(), claims);

        return new LoginResponseDto(token, LocalDateTime.now(), null, "Token generated successful");
    }

    private Boolean isUserEnable(String username){
        return userRepository.findByUsername(username).isPresent();
    }
}
