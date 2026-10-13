package com.easypeasy.EPS_BACKEND.AUTH.DTO;

import com.easypeasy.EPS_BACKEND.USERS.entity.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class LoginResponseDto {

    private String token;
    private  String email;
    private Role role;

}
