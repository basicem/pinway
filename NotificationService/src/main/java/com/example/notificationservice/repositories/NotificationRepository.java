package com.example.notificationservice.repositories;

import com.example.notificationservice.dto.NotificationOpenOnlyDTO;
import com.example.notificationservice.models.Notification;
import com.example.notificationservice.models.NotificationType;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete


public interface NotificationRepository extends CrudRepository<Notification, Integer> {
    List<Notification> findAll();

    @Query("select n from Notification n where n.open=true order by n.id desc limit 10")
    Iterable<Notification> get10NotificationsByOpen();

    @Transactional
    @Modifying
    @Query("update Notification n set n.open= :open where n.id = :id")
    Integer partialUpdateOpen(Integer id, Boolean open);

    Iterable<Notification> findByUserIdAndOpen(Integer id, Boolean open);

}