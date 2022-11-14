package com.github.gideonchen.rover.configuration;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DirectExchangeConfiguration {

    private static String directExchange;

    @Value("${broker.exchange.direct.rover.name}")
    private void setDirectExchangeName(String directExchange) {
        DirectExchangeConfiguration.directExchange = directExchange;
    }

    @Bean
    DirectExchange directExchange() {
        return new DirectExchange(DirectExchangeConfiguration.directExchange);
    }
}
