package com.example.banking_web.controller;

import com.example.banking.model.Account;
import com.example.banking.model.Transaction;
import com.example.banking_web.repository.AccountRepository;
import com.example.banking_web.repository.TransactionRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class AccountController {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public AccountController(AccountRepository accountRepository,
                             TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    @GetMapping("/api/account")
    public Account getAccount(@RequestParam String accountNumber) {

        Account account = accountRepository.findByAccountNumber(accountNumber);

        if (account == null) {
            throw new RuntimeException("Account not found");
        }

        return account;
    }

    @PostMapping("/api/account/deposit")
    public Account deposit(@RequestParam String accountNumber,
                           @RequestParam double amount) {

        Account account = accountRepository.findByAccountNumber(accountNumber);

        if (account == null) {
            throw new RuntimeException("Account not found");
        }

        if (amount <= 0) {
            throw new RuntimeException("Invalid deposit amount");
        }

        account.setBalance(account.getBalance() + amount);

        Account savedAccount = accountRepository.save(account);

        Transaction transaction = new Transaction(
                account.getAccountNumber(),
                "DEPOSIT",
                amount,
                account.getBalance(),
                LocalDateTime.now()
        );

        transactionRepository.save(transaction);

        return savedAccount;
    }

    @PostMapping("/api/account/withdraw")
    public Account withdraw(@RequestParam String accountNumber,
                            @RequestParam double amount) {

        Account account = accountRepository.findByAccountNumber(accountNumber);

        if (account == null) {
            throw new RuntimeException("Account not found");
        }

        if (amount <= 0) {
            throw new RuntimeException("Invalid withdrawal amount");
        }

        if (amount > account.getBalance()) {
            throw new RuntimeException("Insufficient balance");
        }

        account.setBalance(account.getBalance() - amount);

        Account savedAccount = accountRepository.save(account);

        Transaction transaction = new Transaction(
                account.getAccountNumber(),
                "WITHDRAW",
                amount,
                account.getBalance(),
                LocalDateTime.now()
        );

        transactionRepository.save(transaction);

        return savedAccount;
    }

    @GetMapping("/api/transactions")
    public List<Transaction> getTransactions(
            @RequestParam String accountNumber) {

        return transactionRepository
                .findByAccountNumberOrderByDateDesc(accountNumber);
    }
}
