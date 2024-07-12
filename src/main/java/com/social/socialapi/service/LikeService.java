package com.social.socialapi.service;

import com.social.socialapi.dto.request.LikeDTO;
import com.social.socialapi.entity.post.Like;

import java.util.List;

public interface LikeService {
    public Like addLike(LikeDTO likeDTO);

    public void deleteLike(int likeId);

    public Like getLike(int likeId);

    public List<Like> getLikesByComment(int commentId);
}