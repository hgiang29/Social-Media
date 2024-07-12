package com.social.socialapi.repository.message;

import com.social.socialapi.entity.user.User;
import com.social.socialapi.entity.message.RoomMessage;
import com.social.socialapi.entity.message.RoomMessageUser;
import com.social.socialapi.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@DataJpaTest
@Rollback(value = false)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RoomMessageUserRepositoryTest {

    @Autowired
    RoomMessageUserRepository roomMessageUserRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoomMessageRepository roomMessageRepository;

    @Test
    void addParticipantToRoomMessage() {
        User participant = userRepository.findById(3);
        RoomMessage roomMessage = roomMessageRepository.findById(1);
        RoomMessageUser roomMessageUser = new RoomMessageUser(roomMessage, participant);

        roomMessageUserRepository.save(roomMessageUser);
    }

    @Test
    void listAllParticipantInRoomMessage() {
        RoomMessage roomMessage = roomMessageRepository.findById(1);

        List<RoomMessageUser> roomMessageUserList = roomMessageUserRepository.findAllByRoomMessage(roomMessage);
        System.out.println(roomMessageUserList);
    }

    @Test
    void listAllRoomMessageByUser() {
        User user = userRepository.findById(3);
        List<RoomMessageUser> roomMessageUserList = roomMessageUserRepository.findAllByUser(user);
        roomMessageUserList.forEach(roomMessageUser -> System.out.println(roomMessageUser.getRoomMessage()));
    }

}
