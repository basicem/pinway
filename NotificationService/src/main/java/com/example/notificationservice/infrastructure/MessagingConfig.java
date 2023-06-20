package com.example.notificationservice.infrastructure;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
//public class MessagingConfig {
//    public static final String QUEUE = "comment";
//    public static final String EXCHANGE = "comment_exchange";
//    public static final String ROUTING_KEY = "comment_routingKey";
//
//
//    public static final String REVERSE_QUEUE = "revert_comment";
//    public static final String REVERSE_EXCHANGE = "revert_comment_exchange";
//    public static final String REVERSE_ROUTING_KEY = "revert_comment_routingKey";
//
//    @Bean
//    public Queue queue() {
//        return new Queue(QUEUE);
//    }
//
//    @Bean
//    public Queue queueReverse() {
//        return new Queue(REVERSE_QUEUE);
//    }
//
//    @Bean
//    public DirectExchange exchange() {
//        return new DirectExchange(EXCHANGE);
//    }
//
//    @Bean
//    public DirectExchange exchangeReverse() {
//        return new DirectExchange(REVERSE_EXCHANGE);
//    }
//
//    @Bean
//    public Binding binding(Queue queue, DirectExchange exchange) {
//        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
//    }
//
//    @Bean
//    public Binding bindingReverse(Queue queueReverse, DirectExchange exchangeReverse) {
//        return BindingBuilder.bind(queueReverse).to(exchangeReverse).with(REVERSE_ROUTING_KEY);
//    }
//
//    @Bean
//    public MessageConverter converter() {
//        return new Jackson2JsonMessageConverter();
//    }
//    @Bean
//    public AmqpTemplate template(ConnectionFactory connectionFactory) {
//        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
//        rabbitTemplate.setMessageConverter(converter());
//        return rabbitTemplate;
//    }
//}

@Configuration
public class MessagingConfig {
    public static final String QUEUE = "comment";
    public static final String EXCHANGE = "comment_exchange";
    public static final String ROUTING_KEY = "comment_routingKey";


    public static final String REVERSE_QUEUE = "revert_comment";
    public static final String REVERSE_EXCHANGE = "revert_comment_exchange";
    public static final String REVERSE_ROUTING_KEY = "revert_comment_routingKey";

    public static final String QUEUE_LIKE = "like";
    public static final String EXCHANGE_LIKE = "like_exchange";
    public static final String ROUTING_KEY_LIKE = "like_routingKey";

    public static final String REVERSE_QUEUE_LIKE = "revert_like";
    public static final String REVERSE_EXCHANGE_LIKE = "revert_like_exchange";
    public static final String REVERSE_ROUTING_KEY_LIKE = "revert_like_routingKey";

    public static final String QUEUE_PIN = "pin";
    public static final String EXCHANGE_PIN = "pin_exchange";
    public static final String ROUTING_KEY_PIN = "pin_routingKey";

    public static final String REVERSE_QUEUE_PIN = "revert_pin";
    public static final String REVERSE_EXCHANGE_PIN = "revert_pin_exchange";
    public static final String REVERSE_ROUTING_KEY_PIN = "revert_pin_routingKey";

    public static final String QUEUE_FOLLOW = "follow";
    public static final String EXCHANGE_FOLLOW = "follow_exchange";
    public static final String ROUTING_KEY_FOLLOW = "follow_routingKey";

//    public static final String REVERSE_QUEUE_FOLLOW = "revert_follow";
//    public static final String REVERSE_EXCHANGE_FOLLOW = "revert_follow_exchange";
//    public static final String REVERSE_ROUTING_KEY_FOLLOW = "revert_follow_routingKey";


    // comment
    @Bean
    public Queue queue() { return new Queue(QUEUE); }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(EXCHANGE);
    }

    @Bean
    public Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }


    // comment reverse
    @Bean
    public Queue queueReverse() {
        return new Queue(REVERSE_QUEUE);
    }

    @Bean
    public DirectExchange exchangeReverse() {
        return new DirectExchange(REVERSE_EXCHANGE);
    }

    @Bean
    public Binding bindingReverse(Queue queueReverse, DirectExchange exchangeReverse) {
        return BindingBuilder.bind(queueReverse).to(exchangeReverse).with(REVERSE_ROUTING_KEY);
    }

    // like

    @Bean
    public Queue queueLike() { return new Queue(QUEUE_LIKE);}

    @Bean
    public DirectExchange exchangeLike() {
        return new DirectExchange(EXCHANGE_LIKE);
    }

    @Bean
    public Binding bindingLike(Queue queueLike, DirectExchange exchangeLike) {
        return BindingBuilder.bind(queueLike).to(exchangeLike).with(ROUTING_KEY_LIKE);
    }

    // like reverse

    @Bean
    public Queue queueLikeReverse() { return new Queue(REVERSE_QUEUE_LIKE);}

    @Bean
    public DirectExchange exchangeLikeReverse() {
        return new DirectExchange(REVERSE_EXCHANGE_LIKE);
    }

    @Bean
    public Binding bindingLikeReverse(Queue queueLikeReverse, DirectExchange exchangeLikeReverse) {
        return BindingBuilder.bind(queueLikeReverse).to(exchangeLikeReverse).with(REVERSE_ROUTING_KEY_LIKE);
    }

    // pin
    @Bean
    public Queue queuePin() { return new Queue(QUEUE_PIN); }

    @Bean
    public DirectExchange exchangePin() {
        return new DirectExchange(EXCHANGE_PIN);
    }

    @Bean
    public Binding bindingPin(Queue queuePin, DirectExchange exchangePin) {
        return BindingBuilder.bind(queuePin).to(exchangePin).with(ROUTING_KEY_PIN);
    }

    // pin reverse

    @Bean
    public Queue queuePinReverse() { return new Queue(REVERSE_QUEUE_PIN); }

    @Bean
    public DirectExchange exchangePinReverse() {
        return new DirectExchange(REVERSE_EXCHANGE_PIN);
    }

    @Bean
    public Binding bindingPinReverse(Queue queuePinReverse, DirectExchange exchangePinReverse) {
        return BindingBuilder.bind(queuePinReverse).to(exchangePinReverse).with(REVERSE_ROUTING_KEY_PIN);
    }

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
//



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

