package com.example.collectionservice.infrastructure;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MessagingConfig {

    public static final String QUEUE_PIN = "pin";
    public static final String EXCHANGE_PIN = "pin_exchange";
    public static final String ROUTING_KEY_PIN = "pin_routingKey";

    public static final String REVERSE_QUEUE_PIN = "revert_pin";
    public static final String REVERSE_EXCHANGE_PIN = "revert_pin_exchange";
    public static final String REVERSE_ROUTING_KEY_PIN = "revert_pin_routingKey";




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
