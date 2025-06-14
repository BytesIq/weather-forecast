package com.example.userprofile.dto;

import lombok.Getter;
import lombok.Setter;

public class UserRequest {
    private String fullName;
    private String emailId;
    private String password;

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmailId() {
        return emailId;
    }

    public String getPassword() {
        return password;
    }
}
