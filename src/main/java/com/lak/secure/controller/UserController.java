package com.lak.secure.controller;

import com.lak.secure.dto.RegisterRequestDto;
import com.lak.secure.dto.RegisterResponseDto;
import com.lak.secure.entity.UserEntity;
import com.lak.secure.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final AuthService authService;

    @GetMapping
    public List<UserEntity> getAllUsers(){
        return authService.getAllUsers();
    }


}
