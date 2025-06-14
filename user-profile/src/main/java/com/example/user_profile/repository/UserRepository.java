package com.example.user_profile.repository;

import com.example.user_profile.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
