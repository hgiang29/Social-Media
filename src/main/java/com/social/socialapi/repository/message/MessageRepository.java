package com.social.socialapi.repository.message;

import com.social.socialapi.entity.message.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<Message, Integer> {
}
