package com.social.socialapi.repository.message;

import com.social.socialapi.entity.User;
import com.social.socialapi.entity.message.Message;
import com.social.socialapi.entity.message.RoomMessage;
import com.social.socialapi.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@DataJpaTest
@Rollback(value = false)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MessageRepositoryTest {

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoomMessageUserRepository roomMessageUserRepository;

    @Autowired
    RoomMessageRepository roomMessageRepository;

    @Test
    void createMessage() {
        User participant = userRepository.findById(3).
                orElseThrow(() -> new EntityNotFoundException(String.valueOf(3)));
        RoomMessage roomMessage = roomMessageRepository.findById(1).
                orElseThrow(() -> new EntityNotFoundException(String.valueOf(1)));

        // check if user is in the chat room
        if (roomMessageUserRepository.findByRoomMessageAndUser(roomMessage, participant) != null) {
            Message message = new Message(participant, roomMessage, "the first message ever 2");
            messageRepository.save(message);
        }
    }

    @Test
    void listAllMessagesInChatRoom() {
        RoomMessage roomMessage = roomMessageRepository.findById(1).
                orElseThrow(() -> new EntityNotFoundException(String.valueOf(1)));

        List<Message> messageList = messageRepository.findAllByRoomMessageOrderByCreatedAt(roomMessage);
        System.out.println(messageList);
    }

}
