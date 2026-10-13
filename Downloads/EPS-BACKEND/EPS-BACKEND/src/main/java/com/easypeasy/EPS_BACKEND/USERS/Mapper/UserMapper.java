package com.easypeasy.EPS_BACKEND.USERS.Mapper;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;




import com.easypeasy.EPS_BACKEND.USERS.dto.UserRequestDto;
import com.easypeasy.EPS_BACKEND.USERS.dto.UserResponseDto;
import com.easypeasy.EPS_BACKEND.USERS.entity.Role;
import com.easypeasy.EPS_BACKEND.USERS.entity.User;

@Component
public class UserMapper {

    public User toEntity(UserRequestDto dto,Role role) {

        User user = new User();

        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setRole(role);


        return user;
    }


    public UserResponseDto toResponse(User user) {

        UserResponseDto response = new UserResponseDto();

        response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setRole(user.getRole());

        return response;
    }
}