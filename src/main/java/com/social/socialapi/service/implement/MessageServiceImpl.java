package com.social.socialapi.service.implement;

import com.social.socialapi.dto.request.MessageCreationDTO;
import com.social.socialapi.dto.response.MessageViewDTO;
import com.social.socialapi.dto.response.RecentMessageDTO;
import com.social.socialapi.dto.response.UserViewDTO;
import com.social.socialapi.entity.user.User;
import com.social.socialapi.dto.request.RoomMessageCreationDTO;
import com.social.socialapi.dto.request.RoomMessageUserCreationDTO;
import com.social.socialapi.entity.message.Message;
import com.social.socialapi.entity.message.RoomMessage;
import com.social.socialapi.entity.message.RoomMessageUser;
import com.social.socialapi.repository.UserRepository;
import com.social.socialapi.repository.message.MessageRepository;
import com.social.socialapi.repository.message.RoomMessageRepository;
import com.social.socialapi.repository.message.RoomMessageUserRepository;
import com.social.socialapi.service.MessageService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    private final UserRepository userRepository;

    private final MessageRepository messageRepository;

    private final RoomMessageRepository roomMessageRepository;

    private final RoomMessageUserRepository roomMessageUserRepository;

    private final ModelMapper mapper;

    private final int systemUserId = 17;

    public MessageServiceImpl(UserRepository userRepository, MessageRepository messageRepository, RoomMessageRepository roomMessageRepository, RoomMessageUserRepository roomMessageUserRepository, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
        this.roomMessageRepository = roomMessageRepository;
        this.roomMessageUserRepository = roomMessageUserRepository;
        this.mapper = mapper;
    }

    @Override
    public void createRoomMessage(int adminId, RoomMessageCreationDTO roomMessageCreationDTO) {
        User admin = userRepository.findById(adminId);

        // create message room
        RoomMessage roomMessage = new RoomMessage(roomMessageCreationDTO.getRoomMessageName(), admin);
        roomMessageRepository.save(roomMessage);

        // add participants to message room
        List<Integer> participantIds = roomMessageCreationDTO.getParticipantIds();
        participantIds.add(adminId);
        participantIds.add(systemUserId);

        RoomMessageUserCreationDTO roomMessageUserCreationDTO = new RoomMessageUserCreationDTO(roomMessage.getId(), participantIds);
        this.addRoomMessageParticipant(roomMessageUserCreationDTO);

        // system  user create first message
        MessageCreationDTO messageCreationDTO = new MessageCreationDTO(admin.getFirstName() + " da them ban vao nhom chat!", systemUserId, roomMessage.getId());
        this.createMessage(messageCreationDTO);
    }

    @Override
    public void addRoomMessageParticipant(RoomMessageUserCreationDTO roomMessageUserCreationDTO) {
        List<Integer> participantIds = roomMessageUserCreationDTO.getUserIdList();
        RoomMessage roomMessage = roomMessageRepository.findById(roomMessageUserCreationDTO.getRoomMessageId());

        participantIds.forEach(participantId -> {
            User participant = userRepository.findById(participantId).get();
            RoomMessageUser roomMessageUser = new RoomMessageUser(roomMessage, participant);
            roomMessageUserRepository.save(roomMessageUser);
        });
    }

    @Override
    public String createMessage(MessageCreationDTO messageCreationDTO) {
        User participant = userRepository.findById(messageCreationDTO.getSenderId());

        RoomMessage roomMessage = roomMessageRepository.findById(messageCreationDTO.getRoomMessageId());

        // check if user is in the chat room
        if (roomMessageUserRepository.findByRoomMessageAndUser(roomMessage, participant) != null) {
            Message message = new Message(participant, roomMessage, messageCreationDTO.getMessageContent());
            messageRepository.save(message);

            return "Tao tin nhan thanh cong";
        }

        return "Tao tin nhan KHONG thanh cong";
    }

    public List<List<MessageViewDTO>> listAllMessagesInRoomMessageByUser(int roomMessageId) {
        RoomMessage roomMessage = roomMessageRepository.findById(roomMessageId);

        List<Message> messages = messageRepository.findAllByRoomMessageOrderByCreatedAt(roomMessage);
        List<MessageViewDTO> messageViewDTOList = new ArrayList<>();
        messages.forEach(message -> {
            MessageViewDTO messageViewDTO = mapper.map(message, MessageViewDTO.class);
            messageViewDTOList.add(messageViewDTO);
        });

        return groupMessagesByUserList(messageViewDTOList);
    }

    @Override
    public List<MessageViewDTO> listAllMessagesInRoomMessage(int roomMessageId) {
        RoomMessage roomMessage = roomMessageRepository.findById(roomMessageId);

        List<Message> messages = messageRepository.findAllByRoomMessageOrderByCreatedAt(roomMessage);
        List<MessageViewDTO> messageViewDTOList = new ArrayList<>();
        messages.forEach(message -> {
            MessageViewDTO messageViewDTO = mapper.map(message, MessageViewDTO.class);
            messageViewDTOList.add(messageViewDTO);
        });

        return messageViewDTOList;
    }

    @Override
    public List<RoomMessage> findAllRoomMessageByUser(int userId) {
        User user = userRepository.findById(userId);

        List<RoomMessageUser> roomMessageUserList = roomMessageUserRepository.findAllByUser(user);
        List<RoomMessage> roomMessageList = new ArrayList<>();
        roomMessageUserList.forEach(roomMessageUser -> roomMessageList.add(roomMessageUser.getRoomMessage()));

        return roomMessageList;

    }

    @Override
    public List<UserViewDTO> getMessageRoomParticipant(int roomMessageId) {
        RoomMessage roomMessage = roomMessageRepository.findById(roomMessageId);

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

    @Override
    public MessageViewDTO getMessageViewDTOByMessageCreation(MessageCreationDTO messageCreationDTO) {
        User user = userRepository.findById(messageCreationDTO.getSenderId());
        UserViewDTO userViewDTO = mapper.map(user, UserViewDTO.class);

        // random message id
        int messageId = 1023;
        return new MessageViewDTO(messageId, messageCreationDTO.getMessageContent(), new Date(), userViewDTO);

    }


}
