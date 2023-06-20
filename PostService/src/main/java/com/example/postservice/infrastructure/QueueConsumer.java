package com.example.postservice.infrastructure;


import com.example.postservice.dto.CommentInfo;
import com.example.postservice.dto.LikeInfo;
import com.example.postservice.services.CommentService;
import com.example.postservice.services.LikeService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class QueueConsumer {

    @Autowired
    CommentService commentService;

    @Autowired
    LikeService likeService;

    @RabbitListener(queues = MessagingConfig.REVERSE_QUEUE)
    public void receiveComment(CommentInfo commentInfo){
        if (commentInfo.getMessage().equals("delete")) {
            try {
                commentService.Delete(commentInfo.getId());
            } catch (Exception e) {
                System.out.println("Greska u brisanju komentara!");
                System.out.println(e.getMessage());

            }
        }
    }

    @RabbitListener(queues = MessagingConfig.REVERSE_QUEUE_LIKE)
    public void receiveLike(LikeInfo likeInfo){
        if (likeInfo.getMessage().equals("delete")) {
            try {
                likeService.Delete(likeInfo.getId().longValue());
            } catch (Exception e) {
                System.out.println("Greska u brisanju lajka!");
                System.out.println(e.getMessage());

            }
        }
    }
}

