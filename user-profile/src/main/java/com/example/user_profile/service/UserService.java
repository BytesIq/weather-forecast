package com.example.user_profile.service;

import com.example.user_profile.model.User;
import com.example.user_profile.model.UserRequest;
import com.example.user_profile.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void saveUser(UserRequest userRequest) {
        User user = new User();
        user.setFullName(userRequest.getFullName());
        user.setPassword(userRequest.getPassword());
        user.setEmailId(userRequest.getEmailId());

        userRepository.save(user);
    }
}
