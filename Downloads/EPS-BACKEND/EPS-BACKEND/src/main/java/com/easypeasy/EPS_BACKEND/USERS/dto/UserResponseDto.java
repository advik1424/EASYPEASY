package com.easypeasy.EPS_BACKEND.USERS.dto;

import com.easypeasy.EPS_BACKEND.USERS.entity.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter


public class UserResponseDto {


    private Long id;



    private String name;
    private String email;
    private Role role;


}
