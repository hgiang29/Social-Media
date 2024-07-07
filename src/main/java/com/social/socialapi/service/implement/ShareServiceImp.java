package com.social.socialapi.service.implement;

import com.social.socialapi.dto.inputdto.ShareDTO;
import com.social.socialapi.entity.post.Post;
import com.social.socialapi.entity.post.Share;
import com.social.socialapi.repository.ShareRepository;
import com.social.socialapi.service.PostService;
import com.social.socialapi.service.ShareService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
public class ShareServiceImp implements ShareService {
    @Autowired
    public ShareRepository ShareRepository;
    @Autowired
    public PostService postService;
    @Autowired
    private ModelMapper mapper;

    public Share addShare(ShareDTO ShareDTO) {
        Share Share = ConvertShareDTOtoEntity(ShareDTO);
        Share.setCreatedAt(Date.from(Instant.now()));
        Share.setUpdateAt(Date.from(Instant.now()));
        return ShareRepository.save(Share);
    }

    public void deleteShare(int ShareId) {
        try {
            ShareRepository.deleteById(ShareId);

        } catch (Exception e) {
            System.out.println("Error occurred while fetching posts: " + e);
        }
    }

    public Share getShare(int ShareId) {
        return ShareRepository.findById(ShareId).orElse(new Share());
    }

    public Share ConvertShareDTOtoEntity(ShareDTO ShareDTO) {
        Share Share = new Share();
        Post post = postService.getPostById(ShareDTO.getPostId());
        Share.setId(ShareDTO.getId());
        Share.setPost(post);
        return Share;
    }
}
