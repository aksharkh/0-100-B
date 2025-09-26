package com.__100.backend.service.serviceImp;

import com.__100.backend.dto.JwtResponseDto;
import com.__100.backend.dto.LoginRequestDto;
import com.__100.backend.dto.SignupRequestDto;
import com.__100.backend.repository.UserRepository;
import com.__100.backend.service.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthServiceImp implements AuthService {

    private final UserRepository userRepository;

    @Override
    public JwtResponseDto signup(SignupRequestDto signupRequest){

    }

    @Override
    public JwtResponseDto login(LoginRequestDto loginRequest){

    }
}
