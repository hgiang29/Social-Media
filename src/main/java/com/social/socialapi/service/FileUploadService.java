package com.social.socialapi.service;

import com.social.socialapi.dto.outputdto.CloudinaryResponseDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface  FileUploadService {
    CloudinaryResponseDTO uploadFile(MultipartFile multipartFile, final String fileName);
}
