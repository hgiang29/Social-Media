package com.social.socialapi.controller.post;

import com.social.socialapi.dto.request.PostDTO;
import com.social.socialapi.dto.request.ShareDTO;
import com.social.socialapi.entity.post.Share;
import com.social.socialapi.service.PostService;
import com.social.socialapi.service.ShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ShareController {
    @Autowired
    private ShareService shareService;
    @Autowired
    private PostService postService;

    @PostMapping("/post/share")
    public ResponseEntity<ShareDTO> addSharePost(@RequestBody ShareDTO ShareDTO) {
        Share share = shareService.addShare(ShareDTO);
        ShareDTO responseShareDTO = shareService.getShare(share.getId()).ConvertShareEntityToDTO();
        return ResponseEntity.ok(responseShareDTO);
    }

    @GetMapping("/post/{userId}/user/shares")
    public List<PostDTO> getSharesForUser(@PathVariable int userId) {
        List<Share> shares = shareService.getShareByUserId(userId);
        List<PostDTO> postDTOS = new ArrayList<>();
        for (Share share : shares) {
            postDTOS.add(share.getPost().ConvertPostToPostDTO());
        }
        return postDTOS;
    }

}
