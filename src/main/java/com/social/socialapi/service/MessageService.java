package com.social.socialapi.service;

import com.social.socialapi.dto.request.MessageCreationDTO;
import com.social.socialapi.dto.request.RoomMessageCreationDTO;
import com.social.socialapi.dto.request.RoomMessageUserCreationDTO;
import com.social.socialapi.dto.response.MessageViewDTO;
import com.social.socialapi.dto.response.RecentMessageDTO;
import com.social.socialapi.dto.response.UserViewDTO;
import com.social.socialapi.entity.message.RoomMessage;

import java.util.List;

public interface MessageService {

    void createRoomMessage(int adminId, RoomMessageCreationDTO roomMessageCreationDTO);

    void addRoomMessageParticipant(RoomMessageUserCreationDTO roomMessageUserCreationDTO);

    String createMessage(MessageCreationDTO messageCreationDTO);

    List<MessageViewDTO> listAllMessagesInRoomMessage(int roomMessageId);

    List<RoomMessage> findAllRoomMessageByUser(int userId);

    List<UserViewDTO> getMessageRoomParticipant(int roomMessageId);

    // List all the newest user inbox messages
    List<RecentMessageDTO> getRecentMessageList(int userId);

    // For websocket message
    MessageViewDTO getMessageViewDTOByMessageCreation(MessageCreationDTO messageCreationDTO);

}
