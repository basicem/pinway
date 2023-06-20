package com.example.collectionservice.infrastructure;

import com.example.collectionservice.dto.PinInfo;
import com.example.collectionservice.services.CollectionPostService;
import com.example.collectionservice.services.CollectionService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QueueConsumer {

    @Autowired
    CollectionPostService collectionPostService;

    @RabbitListener(queues = MessagingConfig.REVERSE_QUEUE_PIN)
    public void receiveComment(PinInfo pinInfo){
        if (pinInfo.getMessage().equals("delete")) {
            try {
                // ne smije biti pin u kolekciji
                collectionPostService.RemovePost(pinInfo.getCollectionId(), pinInfo.getId());

            } catch (Exception e) {
                System.out.println("Greska u brisanju posta iz kolekcije!");
                System.out.println(e.getMessage());

            }
        }
    }
}
