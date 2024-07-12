package com.social.socialapi.service.implement;

import com.cloudinary.Cloudinary;
import com.social.socialapi.dto.response.CloudinaryResponseDTO;
import com.social.socialapi.exceptions.FuncErrorException;
import com.social.socialapi.service.FileUploadService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class FileUploadServiceImpl implements FileUploadService {

    private final Cloudinary cloudinary;

    //    @Override
//    public String uploadFile(MultipartFile multipartFile) throws IOException {
//        return cloudinary.uploader()
//                .upload(multipartFile.getBytes(),
//                        Map.of("public_id", UUID.randomUUID().toString()))
//                .get("url")
//                .toString();
//    }
    @Transactional
    public CloudinaryResponseDTO uploadFile(MultipartFile file, final String fileName) {
        try {
            final Map result = this.cloudinary.uploader()
                    .upload(file.getBytes(),
                            Map.of("public_id", "hieunm/product/" + fileName, "resource_type", "auto"));
            final String url = result.get("secure_url").toString();
            final String publicID = result.get("public_id").toString();
            return CloudinaryResponseDTO.builder().publicId(publicID).url(url).build();
        } catch (final Exception e) {
            throw new FuncErrorException("Failed to upload file");
        }
    }
}



