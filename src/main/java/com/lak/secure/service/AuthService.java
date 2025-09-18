package com.lak.secure.service;

import com.lak.secure.entity.UserEntity;
import com.lak.secure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public List<UserEntity> getAllUsers(){
        return userRepository.findAll();
    }

    public UserEntity create(UserEntity user){
        user = new UserEntity(user.getName(), user.getUsername(), passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
