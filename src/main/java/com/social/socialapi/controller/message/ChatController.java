package com.social.socialapi.controller.message;

import com.social.socialapi.dto.request.MessageCreationDTO;
import com.social.socialapi.dto.response.MessageViewDTO;
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
    public MessageViewDTO receiveMessage(@Payload MessageCreationDTO message, @DestinationVariable String chatRoom) {
        System.out.println("Received message in room " + chatRoom);
        // messageService.createLiveMessage(message, 1);
        return messageService.getMessageViewDTOByMessageCreation(message);
    }

    @MessageMapping("/private-message")
    public LiveMessage recMessage(@Payload LiveMessage message) {
        simpMessagingTemplate.convertAndSendToUser(String.valueOf(message.getSenderId()), "/private", message);
        System.out.println(message.toString());
        return message;
    }
}

