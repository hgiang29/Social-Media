package com.social.socialapi.repository.message;

import com.social.socialapi.entity.message.Message;
import com.social.socialapi.entity.message.RoomMessage;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepository extends CrudRepository<Message, Integer> {

    List<Message> findAllByRoomMessageOrderByCreatedAt(RoomMessage roomMessage);


}
