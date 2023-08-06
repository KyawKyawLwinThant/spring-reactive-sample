package com.example.userservice.repository;

import com.example.userservice.entity.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface UserRepository extends ReactiveCrudRepository<User,Integer> {
}
