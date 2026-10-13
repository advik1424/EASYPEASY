package com.easypeasy.EPS_BACKEND.AUTH.Controller;

import com.easypeasy.EPS_BACKEND.AUTH.DTO.LoginRequestDto;
import com.easypeasy.EPS_BACKEND.AUTH.DTO.LoginResponseDto;
import com.easypeasy.EPS_BACKEND.AUTH.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {


    private AuthService authService;


    public AuthController (AuthService authService){

        this.authService=authService;

    }

    @PostMapping("/login")

    public LoginResponseDto login(@Valid @RequestBody LoginRequestDto loginRequestDto){


        return authService.login(loginRequestDto);

    }



}
