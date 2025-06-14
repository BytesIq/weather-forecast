package com.example.user_profile.controller;

import com.example.user_profile.model.UserRequest;
import com.example.user_profile.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String registerUser(@RequestBody UserRequest userRequest) {
        userService.saveUser(userRequest);
        return "User registered successfully";
    }
}
