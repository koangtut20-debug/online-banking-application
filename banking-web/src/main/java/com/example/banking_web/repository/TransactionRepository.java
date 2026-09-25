package com.example.banking_web.repository;

import com.example.banking.model.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TransactionRepository extends MongoRepository<Transaction, String> {

    List<Transaction> findByAccountNumberOrderByDateDesc(String accountNumber);
}

