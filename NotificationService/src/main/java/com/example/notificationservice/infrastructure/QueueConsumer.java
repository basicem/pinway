package com.example.notificationservice.infrastructure;


import com.example.notificationservice.dto.*;
import com.example.notificationservice.infrastructure.MessagingConfig;
import com.example.notificationservice.models.Notification;
import com.example.notificationservice.models.NotificationType;
import com.example.notificationservice.services.NotificationService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component
public class QueueConsumer {

    @Autowired
    NotificationService notificationService;

    @Autowired
    private RabbitTemplate rabbitTemplate;


    @RabbitListener(queues = MessagingConfig.QUEUE)
    public void receiveComment(CommentInfo commentInfo){
        if (commentInfo.getMessage().equals("add")) {
            try {
                NotificationType notificationType = notificationService.GetNotificationType("COMMENTED");
                Notification notification = new Notification();
                notification.setOpen(false);
                notification.setContent("User " + commentInfo.getUsername() + " Commented on your Post.");
                notification.setActionUserId(commentInfo.getActionUserId());
                notification.setUserId(commentInfo.getUserId());
                notification.setLikedComment(commentInfo.getId().intValue());
                //the post id of the comment
                notification.setPinnedPost(commentInfo.getPostId().intValue());

                notification.setNotificationType(notificationType);

                notificationService.Create(notification);

            }
            catch (Exception e) {
                System.out.println("Greska u dodavanju notifikacije!");
                System.out.println(e.getMessage());

                CommentInfo info = new CommentInfo(commentInfo.getId(), commentInfo.getContent(), "delete", commentInfo.getPostId(), commentInfo.getUserId(), commentInfo.getUsername(), commentInfo.getActionUserId());
                rabbitTemplate.convertAndSend(MessagingConfig.REVERSE_EXCHANGE, MessagingConfig.REVERSE_ROUTING_KEY, info);
            }
        }
    }

    @RabbitListener(queues = MessagingConfig.QUEUE_LIKE)
    public void receiveLike(LikeInfo likeInfo){
        if (likeInfo.getMessage().equals("add")) {
            try {
                NotificationType notificationType = notificationService.GetNotificationType("LIKED");
                Notification notification = new Notification();
                notification.setOpen(false);
                notification.setContent("User " + likeInfo.getUsername() + " Liked your Comment.");
                notification.setActionUserId(likeInfo.getActionUserId());
                notification.setUserId(likeInfo.getUserId());
                notification.setLikedComment(likeInfo.getId().intValue());
                //the post id of the like
                notification.setPinnedPost(likeInfo.getPostId().intValue());

                notification.setNotificationType(notificationType);

                notificationService.Create(notification);

            }
            catch (Exception e) {
                System.out.println("Greska u dodavanju notifikacije!");
                System.out.println(e.getMessage());

                LikeInfo info = new LikeInfo(likeInfo.getId(), "delete", likeInfo.getPostId(),likeInfo.getUserId(), likeInfo.getUsername(), likeInfo.getActionUserId());
                rabbitTemplate.convertAndSend(MessagingConfig.REVERSE_EXCHANGE_LIKE, MessagingConfig.REVERSE_ROUTING_KEY_LIKE, info);
            }
        }
    }

    @RabbitListener(queues = MessagingConfig.QUEUE_PIN)
    public void receivePin(PinInfo pinInfo){
        if (pinInfo.getMessage().equals("add")) {
            try {
                NotificationType notificationType = notificationService.GetNotificationType("PINNED");
                Notification notification = new Notification();
                notification.setOpen(false);
                notification.setContent("User " + pinInfo.getUsername() + " Pinned your Post.");
                notification.setActionUserId(pinInfo.getActionUserId());
                notification.setUserId(pinInfo.getUserId());
                notification.setPinnedPost(pinInfo.getId().intValue());
                notification.setNotificationType(notificationType);
                notificationService.Create(notification);

            }
            catch (Exception e) {
                System.out.println("Greska u dodavanju notifikacije!");
                System.out.println(e.getMessage());

                PinInfo info = new PinInfo(pinInfo.getId(), pinInfo.getCollectionId(), "delete", pinInfo.getUserId(), pinInfo.getUsername(), pinInfo.getActionUserId());
                rabbitTemplate.convertAndSend(MessagingConfig.REVERSE_EXCHANGE_PIN, MessagingConfig.REVERSE_ROUTING_KEY_PIN, info);
            }
        }
    }

    @RabbitListener(queues = MessagingConfig.QUEUE_FOLLOW)
    public void receivePin(FollowInfo followInfo){
        if (followInfo.getMessage().equals("add")) {
            try {
                NotificationType notificationType = notificationService.GetNotificationType("FOLLOWED");
                Notification notification = new Notification();
                notification.setOpen(false);
                notification.setContent("User " + followInfo.getUsername() + " Followed you.");
                notification.setActionUserId(followInfo.getActionUserId());
                notification.setUserId(followInfo.getUserId());
                notification.setNotificationType(notificationType);
                notificationService.Create(notification);

            }
            catch (Exception e) {
                System.out.println("Greska u dodavanju notifikacije!");
                System.out.println(e.getMessage());
            }
        }
    }
}