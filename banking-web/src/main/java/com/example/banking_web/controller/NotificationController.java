package com.example.banking_web.controller;

import com.example.banking.model.Account;
import com.example.banking_web.model.Notification;
import com.example.banking_web.repository.AccountRepository;
import com.example.banking_web.repository.NotificationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationRepository notificationRepository;
    private final AccountRepository accountRepository;

    public NotificationController(
            NotificationRepository notificationRepository,
            AccountRepository accountRepository) {
        this.notificationRepository = notificationRepository;
        this.accountRepository = accountRepository;
    }

    private void requireAccount(String accountNumber) {
        if (accountNumber == null || accountNumber.isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Account number is required");
        }

        Account account =
                accountRepository.findByAccountNumber(accountNumber);

        if (account == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Account not found");
        }
    }

    @GetMapping
    public List<Notification> getNotifications(
            @RequestParam String accountNumber) {
        requireAccount(accountNumber);

        return notificationRepository
                .findByAccountNumberOrderByCreatedAtDesc(accountNumber);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Notification createNotification(
            @RequestParam String accountNumber,
            @RequestBody Map<String, String> body) {
        requireAccount(accountNumber);

        String message = body == null ? null : body.get("message");

        if (message == null || message.isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Message is required");
        }

        if (message.length() > 500) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Message is too long");
        }

        return notificationRepository.save(
                new Notification(accountNumber, message.trim()));
    }

    @PatchMapping("/read")
    public Map<String, String> markAllRead(
            @RequestParam String accountNumber) {
        requireAccount(accountNumber);

        List<Notification> notifications = notificationRepository
                .findByAccountNumberOrderByCreatedAtDesc(accountNumber);

        for (Notification notification : notifications) {
            notification.setRead(true);
        }

        notificationRepository.saveAll(notifications);

        return Map.of("message", "Notifications marked as read");
    }
}
