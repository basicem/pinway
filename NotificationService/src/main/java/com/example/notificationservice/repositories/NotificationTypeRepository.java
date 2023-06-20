package com.example.notificationservice.repositories;

import com.example.notificationservice.models.NotificationType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface NotificationTypeRepository extends CrudRepository<NotificationType, Integer> {
    List<NotificationType> findAll();
    Optional<NotificationType> findOneByType(String type);
}