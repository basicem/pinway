package com.example.postservice.infrastructure;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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

    public static final String REVERSE_QUEUE_LIKE = "like";
    public static final String REVERSE_EXCHANGE_LIKE = "like_exchange";
    public static final String REVERSE_ROUTING_KEY_LIKE = "like_routingKey";

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
