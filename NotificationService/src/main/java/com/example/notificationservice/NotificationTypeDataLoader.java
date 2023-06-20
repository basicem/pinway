package com.example.notificationservice;
import com.example.notificationservice.models.Notification;
import com.example.notificationservice.models.NotificationType;
import com.example.notificationservice.repositories.NotificationRepository;
import com.example.notificationservice.repositories.NotificationTypeRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class NotificationTypeDataLoader {

    private NotificationTypeRepository notificationTypeRepository;

    private NotificationRepository notificationRepository;

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public NotificationTypeDataLoader(NotificationTypeRepository notificationTypeRepository,
                                      NotificationRepository notificationRepository,
                                      JdbcTemplate jdbcTemplate){
        this.notificationTypeRepository = notificationTypeRepository;
        this.notificationRepository = notificationRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    private  void seedNotificationType() {
        String sql = "SELECT id FROM notification_type LIMIT 1";
        List<NotificationType> nt = jdbcTemplate.query(sql, (resultSet, rowNum) -> null);
        if( nt == null || nt.size() == 0){

            NotificationType nt1 = new NotificationType();
            nt1.setType("SHARED");

            NotificationType nt2 = new NotificationType();
            nt2.setType("LIKED");

            NotificationType nt3 = new NotificationType();
            nt3.setType("FOLLOWED");

            NotificationType nt4 = new NotificationType();
            nt4.setType("PINNED");

            NotificationType nt5 = new NotificationType();
            nt5.setType("COMMENTED");


            notificationTypeRepository.save(nt1);
            notificationTypeRepository.save(nt2);
            notificationTypeRepository.save(nt3);
            notificationTypeRepository.save(nt4);
            notificationTypeRepository.save(nt5);
        }
        System.out.println(notificationTypeRepository.count());
    }

    private  void seedNotification() {
        String sql = "SELECT id, content FROM notification LIMIT 1";
        List<Notification> n = jdbcTemplate.query(sql, (resultSet, rowNum) -> null);
        if( n == null || n.size() == 0){

            Optional<NotificationType> optionalNotificationType = notificationTypeRepository.findOneByType("LIKED");
            NotificationType notificationType = new NotificationType();

            if(optionalNotificationType.isPresent())
                notificationType = optionalNotificationType.get();

            Notification n1 = new Notification();
            n1.setOpen(false);
            n1.setContent("User1 Liked your Comment.");
            n1.setLikedComment(0);
            n1.setActionUserId(1);
            n1.setUserId(3);
            n1.setNotificationType(notificationType);

            optionalNotificationType = notificationTypeRepository.findOneByType("FOLLOWED");
            notificationType = new NotificationType();

            if(optionalNotificationType.isPresent())
                notificationType = optionalNotificationType.get();

            Notification n2 = new Notification();
            n2.setOpen(false);
            n2.setContent("User1 started following you.");
            n2.setActionUserId(1);
            n2.setUserId(3);
            n2.setNotificationType(notificationType);

            optionalNotificationType = notificationTypeRepository.findOneByType("PINNED");
            notificationType = new NotificationType();

            if(optionalNotificationType.isPresent())
                notificationType = optionalNotificationType.get();

            Notification n3 = new Notification();
            n3.setOpen(false);
            n3.setContent("User1 pinned your post.");
            n3.setActionUserId(1);
            n3.setUserId(3);
            n3.setPinnedPost(2);
            n3.setNotificationType(notificationType);

            notificationRepository.save(n1);
            notificationRepository.save(n2);
            notificationRepository.save(n3);


        }
        System.out.println(notificationRepository.count());
    }

    @EventListener
    public  void seed(ContextRefreshedEvent event){
        seedNotificationType();
        seedNotification();

    }
}
