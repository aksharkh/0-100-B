package com.__100.backend.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequestDto {

    @Email(message = "Email is not valid")
    private String email;

    @NotBlank(message = "Password can't be empty")
    private String password;
}
