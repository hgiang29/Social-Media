package com.social.socialapi.controller;

import com.social.socialapi.dto.inputdto.MessageCreationDTO;
import com.social.socialapi.dto.inputdto.RoomMessageCreationDTO;
import com.social.socialapi.dto.inputdto.RoomMessageUserCreationDTO;
import com.social.socialapi.dto.outputdto.MessageViewDTO;
import com.social.socialapi.dto.outputdto.RecentMessageDTO;
import com.social.socialapi.dto.outputdto.UserViewDTO;
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

    @GetMapping("/message/room/{roomMeasageId}/messages")
    public ResponseEntity<List<MessageViewDTO>> getMessageInRoomMessage(@PathVariable int roomMeasageId) {
        List<MessageViewDTO> messagesViewDTOList = messageService.listAllMessagesInRoomMessage(roomMeasageId);
        return ResponseEntity.ok(messagesViewDTOList);
    }

    @GetMapping("/message/room/{roomId}/participant")
    public ResponseEntity<List<UserViewDTO>> getMessageRoomParticipant(@PathVariable(name = "roomId") int roomMessageId) {
        return ResponseEntity.ok(messageService.getMessageRoomParticipant(roomMessageId));
    }

    @GetMapping("/message/recent")
    public ResponseEntity<List<RecentMessageDTO>> getRecentMessages() {
        // lay user id sau khi authenticate
        return ResponseEntity.ok(messageService.getRecentMessageList(3));
    }


}
