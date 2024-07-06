package com.social.socialapi.controller;


import com.social.socialapi.service.FileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class CloudinaryController {
    private final FileUploadService fileUploadService;

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("image")MultipartFile multipartFile) throws IOException {
        try {
            String imageURL = fileUploadService.uploadFile(multipartFile);
            return imageURL;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

}
