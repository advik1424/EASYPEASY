package com.easypeasy.EPS_BACKEND.USERS.contoller;

import com.easypeasy.EPS_BACKEND.USERS.dto.UserRequestDto;
import com.easypeasy.EPS_BACKEND.USERS.dto.UserResponseDto;
import com.easypeasy.EPS_BACKEND.USERS.entity.Role;
import com.easypeasy.EPS_BACKEND.USERS.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    public UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/student")
    public UserResponseDto createStudent(
            @Valid @RequestBody UserRequestDto userRequestDto) {

        return userService.registerUser(
                userRequestDto,
                Role.STUDENT
        );
    }

    @PostMapping("/shopkeeper")
    public UserResponseDto createShopkeeper(
            @Valid @RequestBody UserRequestDto userRequestDto) {

        return userService.registerUser(
                userRequestDto,
                Role.SHOPKEEPER
        );
    }

    @GetMapping("/{id}")
    public UserResponseDto getuserbyid(@PathVariable long id) {

        return userService.getuserbyid(id);
    }
}