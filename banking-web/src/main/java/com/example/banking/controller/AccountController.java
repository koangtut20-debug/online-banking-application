package com.example.banking.controller;

import com.example.banking.model.Account;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @GetMapping("/api/account")
    public Account getAccount() {

        return new Account(
                "100001",
                "Koang Tut Panom",
                "Savings Account",
                0.00,
                "Active"
        );
    }
}
