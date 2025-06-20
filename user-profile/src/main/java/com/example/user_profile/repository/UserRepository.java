package com.example.user_profile.repository;

import com.example.user_profile.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByFullName(String fullname);

    Optional<User> findByEmail(String email);

    List<User> findByDeletedFalse();
}
