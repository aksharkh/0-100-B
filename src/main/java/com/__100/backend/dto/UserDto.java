package com.__100.backend.dto;


import lombok.Data;

@Data
public class UserDto {

    private Integer id;
    private String userName;
    private String email;
    private String role;
}
