package com.social.socialapi.utils;

import com.social.socialapi.dto.outputdto.UserViewDTO;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {

    public static ResponseEntity<Object> generateAuthenticationResponse(UserViewDTO userViewDTO, String token) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("user", userViewDTO);
        map.put("token", token);

        return ResponseEntity.ok(map);
    }

}
