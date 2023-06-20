package com.example.collectionservice.infrastructure;

import com.example.collectionservice.dto.PinInfo;
import com.example.collectionservice.dto.PostDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void PinCreated(Long postId, Integer collectionId, Integer userId, String username,Integer actionUserId) {
        PinInfo info = new PinInfo(postId, collectionId,"add", userId, username, actionUserId);
        rabbitTemplate.convertAndSend(MessagingConfig.EXCHANGE_PIN, MessagingConfig.ROUTING_KEY_PIN, info);
    }
}
