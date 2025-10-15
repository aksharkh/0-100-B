package com.__100.backend.service.serviceImp;

import com.__100.backend.dto.JwtResponseDto;
import com.__100.backend.dto.LoginRequestDto;
import com.__100.backend.dto.SignupRequestDto;
import com.__100.backend.entity.User;
import com.__100.backend.repository.UserRepository;
import com.__100.backend.service.service.AuthService;
import com.__100.backend.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthServiceImp implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @Override
    public JwtResponseDto signup(SignupRequestDto signupRequest){

        if(userRepository.existsByEmail(signupRequest.getEmail())){
            throw new RuntimeException("Email already registered");
        }

        User user = new User();
        user.setUserName(signupRequest.getUserName());
        user.setEmail(signupRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        user.setRole(signupRequest.getRole());

        userRepository.save(user);

        String token = jwtUtil.generateToken(
                org.springframework.security.core.userdetails.User.builder()
                        .username(user.getEmail())
                        .password(user.getPassword())
                        .roles(user.getRole())
                        .build()
        );

        return new JwtResponseDto(token, user.getEmail());

    }

    @Override
    public JwtResponseDto login(LoginRequestDto loginRequest){

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );

        User user = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow(() -> new RuntimeException("User not found"));

        String token = jwtUtil.generateToken(
                org.springframework.security.core.userdetails.User.builder()
                        .username(user.getEmail())
                        .password(user.getPassword())
                        .roles(user.getRole())
                        .build()
        );

        return  new JwtResponseDto(token, user.getEmail());
    }
}
