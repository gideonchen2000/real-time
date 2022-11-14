package com.github.gideonchen.rover.component;


import lombok.SneakyThrows;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

import static java.lang.Long.*;

@Component
@EnableScheduling
public class UpdateScheduler {

    @Value("${rover.name}")
    private String roverName;
    @Value("${broker.exchange.direct.base.name}")
    private String directExchange;

    @Value("${broker.exchange.direct.base.routing-key}")
    private String directExchangeRoutingKey;

    private Long roverUpdateFrequency;

    @Value("${rover.update-freq}")
    private void setRoverUpdateFrequency(String frequency) {
        this.roverUpdateFrequency = Long.parseLong(frequency);
    }

    private final RabbitTemplate rabbitTemplate;

    public UpdateScheduler(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @SneakyThrows
    @Scheduled(fixedDelay = 1)
    public void sendUpdates() {
        String updateMessage = roverName + ": Update at " + new Date() + " " + ParameterFactory.getParameter();
        rabbitTemplate.convertAndSend(directExchange, directExchangeRoutingKey, updateMessage);
        Thread.sleep(roverUpdateFrequency);
    }

}
