package com.example.user_profile.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.user_profile.model.UserRequest;
import com.example.user_profile.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/register")
	public String registerUser(@RequestBody UserRequest userRequest) {
		
		userService.registerUser(userRequest);
		return "User registered successfully";
	}

}
