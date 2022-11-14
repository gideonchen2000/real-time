package com.github.gideonchen.rover.configuration;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BrokerConfiguration {

    static String roverExchangeQueue;
    static String roverRoutingKey;

    @Value("${broker.exchange.direct.rover.routing-key}")
    private void setRoverRoutingKey(String routingKey) {
        BrokerConfiguration.roverRoutingKey = routingKey;
    }

    @Value("${broker.exchange.queue.name}")
    private void setExchangeQueue(String exchangeQueue) {
        BrokerConfiguration.roverExchangeQueue = exchangeQueue;
    }

    @Bean
    Queue queue() {
        return new Queue(BrokerConfiguration.roverExchangeQueue);
    }

    @Bean
    Binding bindingToDirectExchange(Queue commonQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(commonQueue).to(directExchange).with(BrokerConfiguration.roverRoutingKey);
    }

    @Bean
    Binding bindingToFanoutExchange(Queue commonQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(commonQueue).to(fanoutExchange);
    }

}
