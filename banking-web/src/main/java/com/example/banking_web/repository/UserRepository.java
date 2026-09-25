package com.example.banking_web.repository;

import com.example.banking.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

    User findByEmail(String email);

    User findByAccountNumber(String accountNumber);
}
