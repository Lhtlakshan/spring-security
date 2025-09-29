package com.lak.secure.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class LoginResponseDto {
    private String token;
    private LocalDateTime localDateTime;
    private String error;
    private String message;
}
