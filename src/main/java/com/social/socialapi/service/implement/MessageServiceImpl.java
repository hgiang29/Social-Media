package com.social.socialapi.service.implement;

import com.social.socialapi.entity.User;
import com.social.socialapi.dto.inputdto.RoomMessageCreationDTO;
import com.social.socialapi.dto.inputdto.RoomMessageUserCreationDTO;
import com.social.socialapi.repository.UserRepository;
import com.social.socialapi.repository.message.MessageRepository;
import com.social.socialapi.repository.message.RoomMessageRepository;
import com.social.socialapi.service.MessageService;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {

    private UserRepository userRepository;

    private MessageRepository messageRepository;

    private RoomMessageRepository roomMessageRepository;

    public MessageServiceImpl(UserRepository userRepository, MessageRepository messageRepository, RoomMessageRepository roomMessageRepository) {
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
        this.roomMessageRepository = roomMessageRepository;
    }

    @Override
    public void createRoomMessage(RoomMessageCreationDTO roomMessageCreationDTO) {
        User admin = userRepository.findById(roomMessageCreationDTO.getAdminId()).get();

    }

    @Override
    public void addRoomMessageParticipant(RoomMessageUserCreationDTO roomMessageUserCreationDTO) {

    }
}
