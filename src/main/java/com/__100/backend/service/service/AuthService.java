package com.__100.backend.service.service;

import com.__100.backend.dto.JwtResponseDto;
import com.__100.backend.dto.LoginRequestDto;
import com.__100.backend.dto.SignupRequestDto;

public interface AuthService {

    JwtResponseDto signup(SignupRequestDto signupRequest);
    JwtResponseDto login(LoginRequestDto loginRequest);
}
