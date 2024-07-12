package com.social.socialapi.controller;

import com.social.socialapi.dto.inputdto.MessageCreationDTO;
import com.social.socialapi.dto.inputdto.RoomMessageCreationDTO;
import com.social.socialapi.dto.inputdto.RoomMessageUserCreationDTO;
import com.social.socialapi.dto.outputdto.MessageViewDTO;
import com.social.socialapi.dto.outputdto.RecentMessageDTO;
import com.social.socialapi.dto.outputdto.UserViewDTO;
import com.social.socialapi.service.MessageService;
import com.social.socialapi.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class MessageController {

    private final MessageService messageService;

    private final UserService userService;

    public MessageController(MessageService messageService, UserService userService) {
        this.messageService = messageService;
        this.userService = userService;
    }

    @PostMapping("/message/room")
    public ResponseEntity<String> createMessageRoom(@RequestBody RoomMessageCreationDTO roomMessageCreationDTO, @AuthenticationPrincipal UserDetails userDetails) {
        int meId = userService.getUserIdByUserDetails(userDetails);
        messageService.createRoomMessage(meId, roomMessageCreationDTO);
        return ResponseEntity.ok("Tao nhom chat thanh cong");
    }

    @PostMapping("/message/room/participant")
    public ResponseEntity<String> addParticipantToMessageRoom(@RequestBody RoomMessageUserCreationDTO roomMessageUserCreationDTO) {
        messageService.addRoomMessageParticipant(roomMessageUserCreationDTO);
        return ResponseEntity.ok("Them thanh vien thanh cong");
    }

    @PostMapping("/message")
    public ResponseEntity<String> createMessage(@RequestBody MessageCreationDTO messageCreationDTO, @AuthenticationPrincipal UserDetails userDetails) {
        int userId = userService.getUserIdByUserDetails(userDetails);
        messageCreationDTO.setSenderId(userId);
        String body = messageService.createMessage(messageCreationDTO);
        return ResponseEntity.ok(body);
    }

    @GetMapping("/message/room/{roomMessageId}/messages")
    public ResponseEntity<List<MessageViewDTO>> getMessageInRoomMessage(@PathVariable int roomMessageId) {
        List<MessageViewDTO> messagesViewDTOList = messageService.listAllMessagesInRoomMessage(roomMessageId);
        return ResponseEntity.ok(messagesViewDTOList);
    }

    @GetMapping("/message/room/{roomId}/participant")
    public ResponseEntity<List<UserViewDTO>> getMessageRoomParticipant(@PathVariable(name = "roomId") int roomMessageId) {
        return ResponseEntity.ok(messageService.getMessageRoomParticipant(roomMessageId));
    }

    @GetMapping("/message/recent")
    public ResponseEntity<List<RecentMessageDTO>> getRecentMessages(@AuthenticationPrincipal UserDetails userDetails) {
        // lay user id sau khi authenticate
        int userId = userService.getUserIdByUserDetails(userDetails);
        return ResponseEntity.ok(messageService.getRecentMessageList(userId));
    }


}