package com.social.socialapi.dto.request;

import com.social.socialapi.utils.Base64DecodedMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.List;

public class AddingPostDTO {
    public String content;
    public Integer userId;
    public List<String> fileString;

    public MultipartFile convert(String base64String, String fileName) {
        byte[] decodedBytes = Base64.getDecoder().decode(base64String);
        return new Base64DecodedMultipartFile(decodedBytes, fileName);
    }
}

