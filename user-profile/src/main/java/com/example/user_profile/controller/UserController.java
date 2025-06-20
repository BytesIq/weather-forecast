package com.example.user_profile.controller;

import com.example.user_profile.exception.ErrorResponse;
import com.example.user_profile.model.*;
import com.example.user_profile.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Validated
@Tag(
        name = "REST APIs for User microservice in Weather Forecast Application",
        description = "REST APIs in User microservice to REGISTER, LOGIN, AND UPDATE user"
)
public class UserController {

    @Autowired
    private UserService userService;



    @Operation(
            summary = "Register User REST API",
            description = "REST API to register user in User microservice"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
        ),
        @ApiResponse(
                responseCode = "400",
                description = "HTTP Status BAD REQUEST"
        )
    })
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody UserRequest userRequest) {
        userService.registerUser(userRequest);
        return ResponseEntity.ok("User Registered Successfully..");
    }



    @Operation(
            summary = "Login User REST API",
            description = "REST API to login user in User Microservice"
    )
    @ApiResponses({
            @ApiResponse(
                responseCode = "200",
                description = "HTTP Status OK"
             ),
            @ApiResponse(
                    responseCode = "400",
                    description = "HTTP Status BAD REQUEST",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {

        return ResponseEntity.ok(userService.login(loginRequest));
    }


    @Operation(
            summary = "Update user REST API",
            description = "REST API to update user in user microservice"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
    )
    @PutMapping("/update/{fullName}")
    public ResponseEntity<UpdateResponse> updateUser(@PathVariable("fullName") String fullName, @RequestBody UserRequest updateUserRequest) {
        return ResponseEntity.ok(userService.updateUser(fullName, updateUserRequest));
    }


    @Operation(
            summary = "Soft delete user REST API",
            description = "RESt API to soft delete user in user microservice"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
    )
    @PutMapping("/delete/{fullName}")
    public ResponseEntity<String> softDeleteUser(@PathVariable String fullName) {
        userService.softDeleteUser(fullName);
        return ResponseEntity.ok("User soft deleted successfully");
    }

    @Operation(
            summary = "Get all active users REST API",
            description = "REST API to get all active users in user microservice"
    )
    @GetMapping("/activeUsers")
    public ResponseEntity<List<User>> getAllActiveUsers() {
       List<User> users = userService.getAllActiveUsers();
       return ResponseEntity.ok(users);
    }
}
