package com.social.socialapi.dto.inputdto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class RoomMessageUserCreationDTO {

    private int roomMessageId;

    private List<Integer> userIdList;

    public RoomMessageUserCreationDTO(int roomMessageId, List<Integer> userIdList) {
        this.roomMessageId = roomMessageId;
        this.userIdList = userIdList;
    }
}
