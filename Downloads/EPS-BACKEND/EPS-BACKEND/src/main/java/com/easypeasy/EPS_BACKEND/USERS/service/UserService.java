package com.easypeasy.EPS_BACKEND.USERS.service;


import com.easypeasy.EPS_BACKEND.Exception.DuplicateEmailException;
import com.easypeasy.EPS_BACKEND.Exception.ResourceNotFoundException;
import com.easypeasy.EPS_BACKEND.USERS.Mapper.UserMapper;
import com.easypeasy.EPS_BACKEND.USERS.dto.UserRequestDto;
import com.easypeasy.EPS_BACKEND.USERS.dto.UserResponseDto;
import com.easypeasy.EPS_BACKEND.USERS.entity.Role;
import com.easypeasy.EPS_BACKEND.USERS.entity.User;
import com.easypeasy.EPS_BACKEND.USERS.reposistory.UserReposistory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.lang.module.ResolutionException;

@Service
public class UserService {

    private UserReposistory userReposistory;

    private UserMapper userMapper;

    private PasswordEncoder passwordEncoder;



    public UserService(UserReposistory userReposistory,UserMapper userMapper,PasswordEncoder passwordEncoder){

        this.userReposistory=userReposistory;

        this.userMapper=userMapper;
        this.passwordEncoder=passwordEncoder;



    }

    public UserResponseDto registerUser(UserRequestDto requestDto, Role role)


    {

        if (userReposistory.existsByEmail(requestDto.getEmail())) {
            throw new DuplicateEmailException("Email already exists");
        }


        User user= userMapper.toEntity(requestDto,role);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User saveuser=userReposistory.save(user);

        return userMapper.toResponse(saveuser);




    }

    public UserResponseDto getuserbyid( long id){

        User user=userReposistory.findById(id).orElseThrow(()-> new ResourceNotFoundException("This id is not existed"));

        return userMapper.toResponse(user);

    }


}
