package com.social.socialapi.dto.inputdto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RoomMessageCreationDTO {

    private String roomMessageName;

    private List<Integer> participantIds;

}
