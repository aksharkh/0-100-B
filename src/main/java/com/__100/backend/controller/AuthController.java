package com.__100.backend.controller;


import com.__100.backend.dto.JwtResponseDto;
import com.__100.backend.dto.LoginRequestDto;
import com.__100.backend.dto.SignupRequestDto;
import com.__100.backend.service.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<JwtResponseDto> signup(@RequestBody SignupRequestDto signupRequest){
        return ResponseEntity.ok(authService.signup(signupRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponseDto> login(@RequestBody LoginRequestDto loginRequest){
        return ResponseEntity.ok(authService.login(loginRequest));
    }
}
