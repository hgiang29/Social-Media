package com.social.socialapi.service;

import com.social.socialapi.entity.post.Like;
import com.social.socialapi.entity.post.Post;

public interface LikeService {
    public void addLike(Like like);
    public void deleteLike(int likeId);
    public Like getLike(int likeId);
}