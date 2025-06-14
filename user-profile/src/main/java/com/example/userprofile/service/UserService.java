package com.example.userprofile.service;

import com.example.userprofile.dto.UserRequest;
import com.example.userprofile.model.User;
import com.example.userprofile.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private  UserRepository userRepository;


    public String registerUser(UserRequest userRequest) {
        User user = new User();
        user.setEmailId(userRequest.getEmailId());
        user.setFullName(userRequest.getFullName());
        user.setPassword(userRequest.getPassword());
        userRepository.save(user);
        return "user register successfully";
    }
}
