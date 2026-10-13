package com.easypeasy.EPS_BACKEND.AUTH.service;

import com.easypeasy.EPS_BACKEND.Exception.InvalidCredentialsException;
import com.easypeasy.EPS_BACKEND.USERS.entity.User;
import com.easypeasy.EPS_BACKEND.USERS.reposistory.UserReposistory;
import com.easypeasy.EPS_BACKEND.AUTH.DTO.LoginRequestDto;
import com.easypeasy.EPS_BACKEND.AUTH.DTO.LoginResponseDto;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserReposistory userReposistory;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(
            UserReposistory userReposistory,
            PasswordEncoder passwordEncoder,
            JwtService jwtService) {

        this.userReposistory = userReposistory;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public LoginResponseDto login(LoginRequestDto request) {

        // 1. Find user by email
        User user = userReposistory.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new InvalidCredentialsException(
                                "Invalid email or password"
                        )
                );

        // 2. Verify password
        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword())) {

            throw new InvalidCredentialsException(
                    "Invalid email or password"
            );
        }

        // 3. Generate JWT
        String token = jwtService.generateToken(user);

        // 4. Create response
        LoginResponseDto response = new LoginResponseDto();

        response.setToken(token);
        response.setEmail(user.getEmail());
        response.setRole(user.getRole());

        return response;
    }
}