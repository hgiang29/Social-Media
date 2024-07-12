package com.social.socialapi.repository.message;

import com.social.socialapi.entity.message.RoomMessage;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoomMessageRepository extends CrudRepository<RoomMessage, Integer> {

    RoomMessage findById(int id);

    @Query(value = "SELECT rm.id FROM room_message rm JOIN messages m ON rm.id = m.room_message_id GROUP BY rm.id ORDER BY MAX(m.created_at) DESC",
            nativeQuery = true)
    List<Integer> getLatestRoomMessage();
}
