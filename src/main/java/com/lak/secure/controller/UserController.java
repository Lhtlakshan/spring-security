package com.lak.secure.controller;

import com.lak.secure.service.JWTService;
import com.lak.secure.service.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final JWTService jwtService;

//    @PostMapping("/register")
//    public Users register(@RequestBody Users user) {
//        return service.register(user);
//    }
//
//    @GetMapping("/all")
//    public List<Users> getAll(){
//        return service.getUsers();
//    }

    @PostMapping("/login")
    public String login(){
        return jwtService.generateToken();
    }

    @GetMapping("/username")
    public String getUsername(@RequestParam String token){
        return jwtService.getUsername(token);
    }

//    @PreAuthorize("hasRole('USER")
//    @GetMapping("/user")
//    public String user(){
//        return "User";
//    }
//
//    @PreAuthorize("hasRole('ADMIN")
//    @GetMapping("/admin")
//    public String admin(){
//        return "Admin";
//    }
}
