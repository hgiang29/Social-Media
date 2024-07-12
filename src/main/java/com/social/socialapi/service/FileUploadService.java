package com.social.socialapi.service;

import com.social.socialapi.dto.response.CloudinaryResponseDTO;
import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
    CloudinaryResponseDTO uploadFile(MultipartFile multipartFile, final String fileName);
}
