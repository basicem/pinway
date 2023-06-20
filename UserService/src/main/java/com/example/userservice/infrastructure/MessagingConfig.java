package com.example.userservice.infrastructure;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MessagingConfig {

    public static final String QUEUE_FOLLOW = "follow";
    public static final String EXCHANGE_FOLLOW = "follow_exchange";
    public static final String ROUTING_KEY_FOLLOW = "follow_routingKey";

//    public static final String REVERSE_QUEUE_FOLLOW = "revert_follow";
//    public static final String REVERSE_EXCHANGE_FOLLOW = "revert_follow_exchange";
//    public static final String REVERSE_ROUTING_KEY_FOLLOW = "revert_follow_routingKey";

    // follow
    @Bean
    public Queue queueFollow() { return new Queue(QUEUE_FOLLOW); }

    @Bean
    public DirectExchange exchangeFollow() {
        return new DirectExchange(EXCHANGE_FOLLOW);
    }

    @Bean
    public Binding bindingFollow(Queue queueFollow, DirectExchange exchangeFollow) {
        return BindingBuilder.bind(queueFollow).to(exchangeFollow).with(ROUTING_KEY_FOLLOW);
    }

    // follow reverse
//    @Bean
//    public Queue queueFollowReverse() { return new Queue(REVERSE_QUEUE_FOLLOW); }
//
//    @Bean
//    public DirectExchange exchangeFollowReverse() {
//        return new DirectExchange(REVERSE_EXCHANGE_FOLLOW);
//    }
//
//    @Bean
//    public Binding bindingFollowReverse(Queue queueFollowReverse, DirectExchange exchangeFollowReverse) {
//        return BindingBuilder.bind(queueFollowReverse).to(exchangeFollowReverse).with(REVERSE_ROUTING_KEY_FOLLOW);
//    }


    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }
    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }

}
