package com.example.banking_web.repository;

import com.example.banking.model.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountRepository extends MongoRepository<Account, String> {

    Account findByAccountNumber(String accountNumber);
}
