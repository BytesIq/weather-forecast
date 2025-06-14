package com.example.user_profile.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.user_profile.model.User;

public interface UserRepository extends MongoRepository<User, String> {

}
