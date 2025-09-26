package com.__100.backend.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignupRequestDto {


    @NotBlank(message = "Name cannot be empty")
    private String userName;

    @Email(message = "Email is not valid")
    private String email;

    @NotBlank(message = "Password cannot be empty")
    private  String password;

}
