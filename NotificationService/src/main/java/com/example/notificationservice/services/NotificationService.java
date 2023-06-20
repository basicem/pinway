package com.example.notificationservice.services;


import com.example.notificationservice.dto.NotificationOpenOnlyDTO;
import com.example.notificationservice.models.Notification;
import com.example.notificationservice.models.NotificationType;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface NotificationService {
    Notification Create(Notification notification);

    Iterable<Notification> List();

    Iterable<Notification> GetNotificationsForUser(Integer id);

    Notification Details(Integer id);

    Boolean Delete(Integer id);

//    Notification Update(Integer id, Notification notification);
    Notification Update(Notification notificationPatched);

    Integer partialUpdateOpen(Integer id, Boolean open);

    Iterable<NotificationType> ListNotificationTypes();

    Iterable<Notification> List10NotificationsByOpen();

    NotificationType GetNotificationType(String type);


}
