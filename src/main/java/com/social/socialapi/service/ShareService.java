package com.social.socialapi.service;

import com.social.socialapi.dto.request.ShareDTO;
import com.social.socialapi.entity.post.Share;

import java.util.List;

public interface ShareService {
    public Share addShare(ShareDTO ShareDTO);

    public List<Share> getShareByUserId(int userId);

    public void deleteShare(int ShareId);

    public Share getShare(int ShareId);
}