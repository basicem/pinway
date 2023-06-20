package com.example.userservice.infrastructure;

import com.example.userservice.dto.FollowInfo;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void FollowCreated(Integer userId, Integer actionUserId, String username) {
        FollowInfo info = new FollowInfo(userId,"add", actionUserId, username);
        rabbitTemplate.convertAndSend(MessagingConfig.EXCHANGE_FOLLOW, MessagingConfig.ROUTING_KEY_FOLLOW, info);
    }
}
