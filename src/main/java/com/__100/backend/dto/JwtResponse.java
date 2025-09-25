package com.__100.backend.dto;


import lombok.Data;

@Data
public class JwtResponse {


    private  String token;
    private String type = "Bearer";
    private  String userName;
    private  String email;
}
