package com.social.socialapi.service.implement;

import com.social.socialapi.dto.inputdto.MessageCreationDTO;
import com.social.socialapi.dto.outputdto.MessageViewDTO;
import com.social.socialapi.dto.outputdto.RecentMessageDTO;
import com.social.socialapi.dto.outputdto.UserViewDTO;
import com.social.socialapi.entity.User;
import com.social.socialapi.dto.inputdto.RoomMessageCreationDTO;
import com.social.socialapi.dto.inputdto.RoomMessageUserCreationDTO;
import com.social.socialapi.entity.message.Message;
import com.social.socialapi.entity.message.RoomMessage;
import com.social.socialapi.entity.message.RoomMessageUser;
import com.social.socialapi.repository.UserRepository;
import com.social.socialapi.repository.message.MessageRepository;
import com.social.socialapi.repository.message.RoomMessageRepository;
import com.social.socialapi.repository.message.RoomMessageUserRepository;
import com.social.socialapi.service.MessageService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    private final UserRepository userRepository;

    private final MessageRepository messageRepository;

    private final RoomMessageRepository roomMessageRepository;

    private final RoomMessageUserRepository roomMessageUserRepository;

    private final ModelMapper mapper;

    public MessageServiceImpl(UserRepository userRepository, MessageRepository messageRepository, RoomMessageRepository roomMessageRepository, RoomMessageUserRepository roomMessageUserRepository, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
        this.roomMessageRepository = roomMessageRepository;
        this.roomMessageUserRepository = roomMessageUserRepository;
        this.mapper = mapper;
    }

    @Override
    public void createRoomMessage(RoomMessageCreationDTO roomMessageCreationDTO) {
        User admin = userRepository.findById(roomMessageCreationDTO.getAdminId()).get();
        RoomMessage roomMessage = new RoomMessage(roomMessageCreationDTO.getName(), admin);
        roomMessageRepository.save(roomMessage);

        RoomMessageUser roomMessageUser = new RoomMessageUser(roomMessage, admin);
        roomMessageUserRepository.save(roomMessageUser);
    }

    @Override
    public void addRoomMessageParticipant(RoomMessageUserCreationDTO roomMessageUserCreationDTO) {
        List<Integer> participantIds = roomMessageUserCreationDTO.getUserIdList();
        RoomMessage roomMessage = roomMessageRepository.findById(roomMessageUserCreationDTO.getRoomMessageId()).get();

        participantIds.forEach(participantId -> {
            User participant = userRepository.findById(participantId).get();
            RoomMessageUser roomMessageUser = new RoomMessageUser(roomMessage, participant);
            roomMessageUserRepository.save(roomMessageUser);
        });
    }

    @Override
    public String createMessage(MessageCreationDTO messageCreationDTO) {
        User participant = userRepository.findById(messageCreationDTO.getSenderId()).
                orElseThrow(() -> new EntityNotFoundException(String.valueOf(messageCreationDTO.getSenderId())));
        RoomMessage roomMessage = roomMessageRepository.findById(messageCreationDTO.getRoomMessageId()).
                orElseThrow(() -> new EntityNotFoundException(String.valueOf(messageCreationDTO.getRoomMessageId())));

        // check if user is in the chat room
        if (roomMessageUserRepository.findByRoomMessageAndUser(roomMessage, participant) != null) {
            Message message = new Message(participant, roomMessage, messageCreationDTO.getMessageContent());
            messageRepository.save(message);

            return "Tao tin nhan thanh cong";
        }

        return "Tao tin nhan KHONG thanh cong";
    }

    @Override
    public List<List<MessageViewDTO>> listAllMessagesInRoomMessage(int roomMessageId) {
        RoomMessage roomMessage = roomMessageRepository.findById(roomMessageId).
                orElseThrow(() -> new EntityNotFoundException(String.valueOf(roomMessageId)));

        List<Message> messages = messageRepository.findAllByRoomMessageOrderByCreatedAt(roomMessage);
        List<MessageViewDTO> messageViewDTOList = new ArrayList<>();
        messages.forEach(message -> {
            MessageViewDTO messageViewDTO = mapper.map(message, MessageViewDTO.class);
            messageViewDTOList.add(messageViewDTO);
        });

        return groupMessagesByUserList(messageViewDTOList);
    }

    @Override
    public List<RoomMessage> findAllRoomMessageByUser(int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(String.valueOf(userId)));

        List<RoomMessageUser> roomMessageUserList = roomMessageUserRepository.findAllByUser(user);
        List<RoomMessage> roomMessageList = new ArrayList<>();
        roomMessageUserList.forEach(roomMessageUser -> roomMessageList.add(roomMessageUser.getRoomMessage()));

        return roomMessageList;

    }

    @Override
    public List<UserViewDTO> getMessageRoomParticipant(int roomMessageId) {
        RoomMessage roomMessage = roomMessageRepository.findById(roomMessageId).
                orElseThrow(() -> new EntityNotFoundException(String.valueOf(roomMessageId)));
        List<RoomMessageUser> roomMessageUserList = roomMessageUserRepository.findAllByRoomMessage(roomMessage);

        List<UserViewDTO> userViewDTOList = new ArrayList<>();
        roomMessageUserList.forEach(rmu -> {
            UserViewDTO userViewDTO = mapper.map(rmu.getUser(), UserViewDTO.class);
            userViewDTOList.add(userViewDTO);
        });

        return userViewDTOList;
    }


    private List<List<MessageViewDTO>> groupMessagesByUserList(List<MessageViewDTO> messageList) {
        List<List<MessageViewDTO>> groupedMessages = new ArrayList<>();

        List<MessageViewDTO> currentGroup = new ArrayList<>();
        int currentSenderId = messageList.get(0).getSender().getId();

        for (MessageViewDTO message : messageList) {
            if (message.getSender().getId() != currentSenderId) {
                groupedMessages.add(new ArrayList<>(currentGroup));
                currentGroup.clear();
                currentSenderId = message.getSender().getId();
            }
            currentGroup.add(message);
        }

        // Ensure the last group is added
        if (!currentGroup.isEmpty()) {
            groupedMessages.add(currentGroup);
        }
        return groupedMessages;
    }

    private RecentMessageDTO getMostRecentMessageByRoom(RoomMessage roomMessage) {
        Message message = messageRepository.findMostRecentMessageByRoomMessage(roomMessage.getId());
        return mapper.map(message, RecentMessageDTO.class);
    }

    @Override
    public List<RecentMessageDTO> getRecentMessageList(int userId) {
        List<RoomMessage> roomMessageList = findAllRoomMessageByUser(userId);
        List<RecentMessageDTO> recentMessageDTOList = new ArrayList<>();

        roomMessageList.forEach(roomMessage -> {
            recentMessageDTOList.add(this.getMostRecentMessageByRoom(roomMessage));
        });

        return recentMessageDTOList;
    }

}
