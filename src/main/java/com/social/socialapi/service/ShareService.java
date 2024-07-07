package com.social.socialapi.service;

import com.social.socialapi.dto.inputdto.ShareDTO;
import com.social.socialapi.entity.post.Share;

public interface ShareService {
    public Share addShare(ShareDTO ShareDTO);

    public void deleteShare(int ShareId);

    public Share getShare(int ShareId);
}