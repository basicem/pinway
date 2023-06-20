package com.example.postservice.infrastructure;

import com.example.postservice.dto.CommentInfo;
import com.example.postservice.dto.LikeInfo;
import com.example.postservice.models.Comment;
import com.example.postservice.models.Like;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void CommentCreated(Comment comment, Long userId, String username) {
        CommentInfo info = new CommentInfo(comment.getId(), comment.getContent(), "add", comment.getPost().getId(),userId.intValue(), username, comment.getUser_id().intValue());
        rabbitTemplate.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTING_KEY, info);
    }

    public void LikeCreated(Like like, Integer userId, String username) {
        LikeInfo info = new LikeInfo(like.getId(), "add", like.getComment().getPost().getId(), userId, username, like.getUser_id().intValue());
        rabbitTemplate.convertAndSend(MessagingConfig.EXCHANGE_LIKE, MessagingConfig.ROUTING_KEY_LIKE, info);
    }

}
