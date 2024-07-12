package com.social.socialapi;

import com.social.socialapi.dto.response.RecentMessageDTO;
import com.social.socialapi.entity.message.Message;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SocialApiApplication {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.typeMap(Message.class, RecentMessageDTO.class).addMappings(mapper -> {
            mapper.map(src -> src.getRoomMessage().getName(),
                    RecentMessageDTO::setRoomMessageName);
            mapper.map(src -> src.getRoomMessage().getId(),
                    RecentMessageDTO::setRoomMessageId);
            mapper.map(Message::getContent,
                    RecentMessageDTO::setLastMessageContent);
        });

        return modelMapper;
    }

    public static void main(String[] args) {
        SpringApplication.run(SocialApiApplication.class, args);
    }

}
