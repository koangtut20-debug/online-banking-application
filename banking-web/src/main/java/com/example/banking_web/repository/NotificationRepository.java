package com.example.banking_web.repository;

import com.example.banking_web.model.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface NotificationRepository
        extends MongoRepository<Notification, String> {

    List<Notification> findByAccountNumberOrderByCreatedAtDesc(
            String accountNumber);
}
