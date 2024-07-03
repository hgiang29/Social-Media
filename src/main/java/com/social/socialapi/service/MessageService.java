package com.social.socialapi.service;

import com.social.socialapi.dto.inputdto.RoomMessageCreationDTO;
import com.social.socialapi.dto.inputdto.RoomMessageUserCreationDTO;

public interface MessageService {

    void createRoomMessage(RoomMessageCreationDTO roomMessageCreationDTO);

    void addRoomMessageParticipant(RoomMessageUserCreationDTO roomMessageUserCreationDTO);
}
