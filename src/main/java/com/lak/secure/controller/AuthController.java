package com.lak.secure.controller;

import com.lak.secure.entity.UserEntity;
import com.lak.secure.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping
    public List<UserEntity> getAllUsers(){
        return authService.getAllUsers();
    }

    @PostMapping
    public UserEntity createUsers(@RequestBody UserEntity user){
        return authService.create(user);
    }
}
