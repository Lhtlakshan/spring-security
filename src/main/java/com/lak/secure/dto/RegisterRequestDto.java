package com.lak.secure.dto;

import com.lak.secure.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
@Setter
public class RegisterRequestDto {
    private String name;
    private String email;
    private String password;
    private UserRole userRole;

}
