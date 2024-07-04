package com.social.socialapi.service.implement;

import com.social.socialapi.entity.post.Like;
import com.social.socialapi.entity.post.Post;
import com.social.socialapi.repository.LikeRepository;
import com.social.socialapi.repository.PostRepository;
import com.social.socialapi.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ListServiceImpl  implements LikeService {
    @Autowired
    public LikeRepository likeRepository;

    public void addLike(Like like){
        try {
            like.setCreatedAt(Date.from(Instant.now()));
            like.setUpdatedAt(Date.from(Instant.now()));
            likeRepository.save(like);

        } catch (Exception e) {
            System.out.println("Error occurred while fetching posts: "+ e);
        }
    }
    public void deleteLike(int likeId){
        try {
            likeRepository.deleteById(likeId);

        } catch (Exception e) {
            System.out.println("Error occurred while fetching posts: "+ e);
        }
    }

    public Like getLike(int likeId) {
        return likeRepository.findById(likeId).orElse(new Like());
    }
}
