package com.social.socialapi.repository.message;

import com.social.socialapi.entity.message.RoomMessage;
import org.springframework.data.repository.CrudRepository;

public interface RoomMessageRepository extends CrudRepository<RoomMessage, Integer> {
}
