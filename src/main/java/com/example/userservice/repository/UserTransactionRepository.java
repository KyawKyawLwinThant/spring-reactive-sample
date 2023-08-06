package com.example.userservice.repository;


import com.example.userservice.entity.UserTransaction;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface UserTransactionRepository extends ReactiveCrudRepository<UserTransaction,Integer> {
}
