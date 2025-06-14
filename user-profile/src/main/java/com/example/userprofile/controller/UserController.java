package com.example.userprofile.controller;

import com.example.userprofile.dto.UserRequest;
import com.example.userprofile.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
   private UserService userService;

    @PostMapping("/register")
    public String register(@RequestBody UserRequest userRequest){
       return userService.registerUser(userRequest);
    }
}
