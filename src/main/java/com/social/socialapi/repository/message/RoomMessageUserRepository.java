package com.social.socialapi.repository.message;

import com.social.socialapi.entity.user.User;
import com.social.socialapi.entity.message.RoomMessage;
import com.social.socialapi.entity.message.RoomMessageUser;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoomMessageUserRepository extends CrudRepository<RoomMessageUser, Integer> {

    List<RoomMessageUser> findAllByRoomMessage(RoomMessage roomMessage);

    RoomMessageUser findByRoomMessageAndUser(RoomMessage roomMessage, User user);

    List<RoomMessageUser> findAllByUser(User user);
}
