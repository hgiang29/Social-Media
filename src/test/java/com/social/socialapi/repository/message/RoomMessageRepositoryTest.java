package com.social.socialapi.repository.message;

import com.social.socialapi.entity.user.User;
import com.social.socialapi.entity.message.RoomMessage;
import com.social.socialapi.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@Rollback(value = false)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RoomMessageRepositoryTest {

    @Autowired
    RoomMessageRepository roomMessageRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    void createRoomMessage() {
        User admin = userRepository.findById(1);
        System.out.println(admin);

        RoomMessage roomMessage = new RoomMessage("phong chat 1", admin);
        roomMessageRepository.save(roomMessage);
    }

    @Test
    void findRoomMessageById() {
        System.out.println(roomMessageRepository.findById(1));
    }
}
