package com.easypeasy.EPS_BACKEND.AUTH.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {


    @GetMapping("/test")
    public String test() {
        return "JWT Authentication Working";
    }

    @GetMapping("/student")
    public String student() {
        return "Student API Working";
    }
}

