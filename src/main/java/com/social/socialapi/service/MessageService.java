package com.social.socialapi.service;

import com.social.socialapi.dto.inputdto.MessageCreationDTO;
import com.social.socialapi.dto.inputdto.RoomMessageCreationDTO;
import com.social.socialapi.dto.inputdto.RoomMessageUserCreationDTO;
import com.social.socialapi.dto.outputdto.MessageViewDTO;
import com.social.socialapi.entity.message.Message;
import com.social.socialapi.entity.message.RoomMessage;

import java.util.List;

public interface MessageService {

    void createRoomMessage(RoomMessageCreationDTO roomMessageCreationDTO);

    void addRoomMessageParticipant(RoomMessageUserCreationDTO roomMessageUserCreationDTO);

    String createMessage(MessageCreationDTO messageCreationDTO);

    List<List<MessageViewDTO>> listAllMessagesInRoomMessage(int roomMessageId);

    List<RoomMessage> findAllRoomMessageByUser(int userId);

    // List all the newest user inbox messages
    void listUserInbox(int userId);

}
