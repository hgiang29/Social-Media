package com.social.socialapi.repository.message;

import com.social.socialapi.entity.message.Message;
import com.social.socialapi.entity.message.RoomMessage;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends CrudRepository<Message, Integer> {

    List<Message> findAllByRoomMessageOrderByCreatedAt(RoomMessage roomMessage);

    @Query(
            value = "SELECT * FROM messages WHERE room_message_id = :roomMessageId ORDER BY created_at DESC LIMIT 1",
            nativeQuery = true)
    Message findMostRecentMessageByRoomMessage(@Param("roomMessageId") int roomMessageId);


}
