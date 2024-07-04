package com.social.socialapi.controller;

import com.social.socialapi.dto.inputdto.MessageCreationDTO;
import com.social.socialapi.dto.inputdto.RoomMessageCreationDTO;
import com.social.socialapi.dto.inputdto.RoomMessageUserCreationDTO;
import com.social.socialapi.dto.outputdto.MessageViewDTO;
import com.social.socialapi.service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/message/room")
    public ResponseEntity<String> createMessageRoom(@RequestBody RoomMessageCreationDTO roomMessageCreationDTO) {
        messageService.createRoomMessage(roomMessageCreationDTO);
        return ResponseEntity.ok("Tao nhom chat thanh cong");
    }

    @PostMapping("/message/room/participant")
    public ResponseEntity<String> addParticipantToMessageRoom(@RequestBody RoomMessageUserCreationDTO roomMessageUserCreationDTO) {
        messageService.addRoomMessageParticipant(roomMessageUserCreationDTO);
        return ResponseEntity.ok("Them thanh vien thanh cong");
    }

    @PostMapping("/message")
    public ResponseEntity<String> createMessage(@RequestBody MessageCreationDTO messageCreationDTO) {
        String body = messageService.createMessage(messageCreationDTO);
        return ResponseEntity.ok(body);
    }

    @GetMapping("/room/{roomMeasageId}/messages")
    public ResponseEntity<List<List<MessageViewDTO>>> getMessageInRoomMessage(@PathVariable int roomMeasageId) {
        List<List<MessageViewDTO>> messagesViewDTOList = messageService.listAllMessagesInRoomMessage(roomMeasageId);
        return ResponseEntity.ok(messagesViewDTOList);
    }


}
