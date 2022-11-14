package com.github.gideonchen.base.component;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageHandler {
    private final RabbitTemplate rabbitTemplate;

    public MessageHandler(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void receiveMessage(String message) {
        System.out.println("> " + message);
    }

    public void sendMessage(String cmd) {
        String to = cmd.split(":")[0];
        String msg = cmd.split(":")[1];
        switch(to){
            case "@gemini":
                rabbitTemplate.convertAndSend("gemini-direct-exchange", "__gemini", "Base-O21: "+msg);
                break;
            case "@soyuz":
                rabbitTemplate.convertAndSend("soyuz-direct-exchange", "__soyuz", "Base-O21: "+msg);
                break;
            case "@orion":
                rabbitTemplate.convertAndSend("orion-direct-exchange", "__orion", "Base-O21: "+msg);
                break;
            case "@all":
                rabbitTemplate.convertAndSend("tyco-fanout-exchange", "","Base: "+msg);
                break;
            default:
                System.out.println("Message format not correct!!");
        }
    }
}
