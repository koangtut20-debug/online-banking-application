package com.example.banking_web.controller;

import com.example.banking.model.Account;
import com.example.banking.model.User;
import com.example.banking_web.repository.AccountRepository;
import com.example.banking_web.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;

    public AuthController(UserRepository userRepository,
                          AccountRepository accountRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }

    @PostMapping("/register")
    public String register(@RequestBody User user) {

        User existingUser = userRepository.findByEmail(user.getEmail());

        if (existingUser != null) {
            return "Email already registered";
        }

        long accountCount = accountRepository.count();
        String accountNumber = String.valueOf(100001 + accountCount);

        user.setAccountNumber(accountNumber);

        userRepository.save(user);

        Account account = new Account(
                accountNumber,
                user.getName(),
                "Savings Account",
                0.00,
                "Active"
        );

        accountRepository.save(account);

        return "Registration successful";
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody User user) {

        User existingUser = userRepository.findByEmail(user.getEmail());

        if (existingUser == null) {
            return new LoginResponse(
                    "Invalid email or password",
                    null,
                    null,
                    null
            );
        }

        System.out.println(
                "DEBUG USER FROM MONGODB = " + existingUser
        );

        System.out.println(
                "DEBUG ACCOUNT NUMBER = " + existingUser.getAccountNumber()
        );

        if (!existingUser.getPassword().equals(user.getPassword())) {
            return new LoginResponse(
                    "Invalid email or password",
                    null,
                    null,
                    null
            );
        }

        return new LoginResponse(
                "Login successful",
                existingUser.getName(),
                existingUser.getEmail(),
                existingUser.getAccountNumber()
        );
    }

    public static class LoginResponse {

        private String message;
        private String name;
        private String email;
        private String accountNumber;

        public LoginResponse(String message,
                             String name,
                             String email,
                             String accountNumber) {
            this.message = message;
            this.name = name;
            this.email = email;
            this.accountNumber = accountNumber;
        }

        public String getMessage() {
            return message;
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }

        public String getAccountNumber() {
            return accountNumber;
        }
    }
}
