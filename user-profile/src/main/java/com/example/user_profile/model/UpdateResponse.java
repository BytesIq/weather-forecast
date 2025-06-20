package com.example.user_profile.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateResponse {

    private String fullName;
    private String email;
    private String password;
}
