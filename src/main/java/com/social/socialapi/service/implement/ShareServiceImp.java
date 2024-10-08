package com.social.socialapi.service.implement;

import com.social.socialapi.dto.request.ShareDTO;
import com.social.socialapi.dto.response.UserViewDTO;
import com.social.socialapi.entity.post.Post;
import com.social.socialapi.entity.post.Share;
import com.social.socialapi.repository.post.PostRepository;
import com.social.socialapi.repository.post.ShareRepository;
import com.social.socialapi.repository.UserRepository;
import com.social.socialapi.service.PostService;
import com.social.socialapi.service.ShareService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
public class ShareServiceImp implements ShareService {
    @Autowired
    public ShareRepository ShareRepository;
    @Autowired
    public PostService postService;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;

    public Share addShare(ShareDTO ShareDTO) {

//        ShareDTO.setPost(postDTO);

        UserViewDTO userDTO = userRepository.findById(ShareDTO.getUser_id()).ConvertEntitytoDTO();
        Post post = postRepository.findById(ShareDTO.getPostId()).orElse(new Post());
        ShareDTO.setShareUser(userDTO);
        Share share = ShareDTO.ConvertShareDTOtoEntity();
        share.setPost(post);
        share.setCreatedAt(Date.from(Instant.now()));
        share.setUpdateAt(Date.from(Instant.now()));
        return ShareRepository.save(share);
    }

    public List<Share> getShareByUserId(int userId) {
        return ShareRepository.getShareByUserId(userId);
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

}
