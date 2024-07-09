package com.social.socialapi.controller.post;

import com.social.socialapi.dto.inputdto.ShareDTO;
import com.social.socialapi.entity.post.Share;
import com.social.socialapi.service.ShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShareController {
    @Autowired
    private ShareService shareService;

    @PostMapping("/post/share")
    public ResponseEntity<ShareDTO> addSharePost(@RequestBody ShareDTO ShareDTO) {
        Share share = shareService.addShare(ShareDTO);
        ShareDTO responseShareDTO = shareService.getShare(share.getId()).ConvertShareEntityToDTO();
        return ResponseEntity.ok(responseShareDTO);
    }


}
