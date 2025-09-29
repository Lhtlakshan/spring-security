package com.lak.secure.controller;

import com.lak.secure.dto.LoginRequestDto;
import com.lak.secure.dto.LoginResponseDto;
import com.lak.secure.dto.RegisterRequestDto;
import com.lak.secure.dto.RegisterResponseDto;
import com.lak.secure.service.AuthService;
import com.lak.secure.service.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auth")
public class AuthController {

    private final JWTService jwtService;
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto){
        LoginResponseDto loginRes = authService.login(loginRequestDto);
        if(loginRes.getError() != null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(loginRes);

        return ResponseEntity.status(HttpStatus.OK).body(loginRes);

    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDto> registerUsers(@RequestBody RegisterRequestDto registerRequest){
        RegisterResponseDto res = authService.registerUser(registerRequest);

        if(res.getError() == null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(res);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
    }

}
