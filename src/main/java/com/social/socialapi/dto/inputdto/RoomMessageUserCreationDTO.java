package com.social.socialapi.dto.inputdto;

import lombok.Getter;

import java.util.List;

@Getter
public class RoomMessageUserCreationDTO {

    private int roomMessageId;

    private List<Integer> userIdList;

}
