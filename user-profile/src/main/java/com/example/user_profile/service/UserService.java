package com.example.user_profile.service;

import com.example.user_profile.exception.UserAlreadyExistException;
import com.example.user_profile.exception.UserNotFoundException;
import com.example.user_profile.model.*;
import com.example.user_profile.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public void registerUser(UserRequest userRequest) {
        Optional<User> existingUser =  userRepository.findByEmail(userRequest.getEmail());
        if(existingUser.isPresent()) {
            throw new UserAlreadyExistException(("User already Exists"));
        }

        User user = new User();
        user.setFullName(userRequest.getFullName());
        user.setPassword(encoder.encode(userRequest.getPassword()));
        user.setEmail(userRequest.getEmail());
        user.setRoles(Set.of("USER"));
        userRepository.save(user);
    }

    public LoginResponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(()-> new UserNotFoundException("User not found"));

        String token =  jwtService.generateToken(user);

        return LoginResponse.builder()
                .token(token)
                .fullName(user.getFullName())
                .roles(user.getRoles())
                .build();
    }

    public UpdateResponse updateUser(String fullName, UserRequest updateUserRequest) {

        User oldUser = userRepository.findByFullName(fullName)
                .orElseThrow(() -> new UserNotFoundException("User not found.."));

        oldUser.setFullName(updateUserRequest.getFullName());
        oldUser.setEmail(updateUserRequest.getEmail());
        oldUser.setPassword(updateUserRequest.getPassword());

        userRepository.save(oldUser);

        return UpdateResponse.builder()
                .fullName(oldUser.getFullName())
                .email(oldUser.getEmail())
                .password(oldUser.getPassword())
                .build();
    }

    public void softDeleteUser(String fullName) {
       User user = userRepository.findByFullName(fullName).
                orElseThrow(() -> new UserNotFoundException("User not found"));

       user.setDeleted(true);
       userRepository.save(user);
    }

    public List<User> getAllActiveUsers() {
        return userRepository.findByDeletedFalse();
    }
}
