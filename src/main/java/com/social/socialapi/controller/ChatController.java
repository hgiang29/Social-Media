package com.social.socialapi.controller;

import com.social.socialapi.entity.message.LiveMessage;
import com.social.socialapi.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private MessageService messageService;


    @MessageMapping("/message/{chatRoom}")
    @SendTo("/room/{chatRoom}")
    public LiveMessage receiveMessage(@Payload LiveMessage message, @DestinationVariable String chatRoom) {
        System.out.println("Received message in room " + chatRoom);
        // messageService.createLiveMessage(message, 1);
        return message;
    }

    @MessageMapping("/private-message")
    public LiveMessage recMessage(@Payload LiveMessage message) {
        simpMessagingTemplate.convertAndSendToUser(message.getReceiverName(), "/private", message);
        System.out.println(message.toString());
        return message;
    }
}

