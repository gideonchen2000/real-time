package com.github.gideonchen.rover;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;

@Configuration
public class ChatInterface implements CommandLineRunner {

    private final RabbitTemplate rabbitTemplate;
    private final Scanner scanner;

    @Value("${rover.name}")
    private String roverName;
    @Value("${broker.exchange.direct.base.name}")
    private String directExchange;
    @Value("${broker.exchange.direct.base.routing-key}")
    private String directExchangeRoutingKey;

    public ChatInterface(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        this.scanner = new Scanner(System.in);
    }


    @Override
    public void run(String... args) {
        System.out.println("Booting up: " + roverName.toUpperCase());
        System.out.println("Please enter the message..");
        while (true) {
            String msg = scanner.nextLine();
            rabbitTemplate.convertAndSend(directExchange, directExchangeRoutingKey, roverName + ": " + msg);
        }
    }
}
