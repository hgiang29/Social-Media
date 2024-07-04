package com.social.socialapi.service;

import com.social.socialapi.dto.inputdto.LikeDTO;
import com.social.socialapi.entity.post.Like;
import com.social.socialapi.entity.post.Post;

public interface LikeService {
    public void addLike(LikeDTO likeDTO);
    public void deleteLike(int likeId);
    public Like getLike(int likeId);
}