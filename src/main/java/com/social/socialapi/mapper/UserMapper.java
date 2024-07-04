package com.social.socialapi.mapper;

import org.mapstruct.Mapper;

import com.social.socialapi.dto.inputdto.UserCreationRequest;
import com.social.socialapi.dto.outputdto.UserResponse;
import com.social.socialapi.entity.User;


@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);

    UserResponse tUserResponse(User user);    
}
