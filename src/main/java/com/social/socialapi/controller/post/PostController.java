package com.social.socialapi.controller.post;

import com.social.socialapi.dto.inputdto.CommentDTO;
import com.social.socialapi.dto.inputdto.LikeDTO;
import com.social.socialapi.dto.inputdto.PostDTO;
import com.social.socialapi.dto.inputdto.ShareDTO;
import com.social.socialapi.entity.post.Comment;
import com.social.socialapi.entity.post.Like;
import com.social.socialapi.entity.post.Post;
import com.social.socialapi.entity.post.Share;
import com.social.socialapi.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;


@RestController
@CrossOrigin
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/posts")
    public ResponseEntity<List<Post>> getAllPost() {

        return ResponseEntity.ok(postService.getAllPosts());
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<PostDTO> getPost(@PathVariable int postId) {
        Post post = new Post();
        try {
            post = postService.getPostById(postId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        PostDTO postDTO = ConvertPostEntityToDTO(post);
        List<LikeDTO> likeDTOList = getLikesForPost(postDTO.getId());
        postDTO.setLikeDTOs(likeDTOList);
        List<ShareDTO> shareDTOList = getSharesForPost(postDTO.getId());
        postDTO.setShareDTOS(shareDTOList);
        return ResponseEntity.ok(postDTO);
    }

//        @GetMapping("jobPosts/keyword/{keyword}")
//        public List<JobPost> searchByKeyword(@PathVariable("keyword") String keyword){
//            return service.search(keyword);
//
//        }

    @PostMapping("/post")
    public ResponseEntity<PostDTO> addPost(@RequestBody PostDTO postDTO) {
        postService.addPost(postDTO);
        PostDTO ResponsePostDTO = ConvertPostEntityToDTO(postService.getPostById(postDTO.getId()));
        return ResponseEntity.ok(ResponsePostDTO);
    }

    @PostMapping("/post/image/{PostId}")
    public ResponseEntity<PostDTO> addImage(@PathVariable final Integer PostId, @RequestPart final MultipartFile file) {
        this.postService.uploadImage(PostId, file);
        return ResponseEntity.ok(ConvertPostEntityToDTO(postService.getPostById(PostId)));
    }

    @PutMapping("/post")
    public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO postDTO) {
        postService.updatePost(postDTO);
        PostDTO ResponsePostDTO = ConvertPostEntityToDTO(postService.getPostById(postDTO.getId()));
        return ResponseEntity.ok(ResponsePostDTO);
    }

    @DeleteMapping("post/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable int postId) {
        postService.deletePost(postId);
        return ResponseEntity.ok("Deleted");
    }

    // region: get Like, share , comment of Posts
    @GetMapping("/post/{postId}/likes")
    public List<LikeDTO> getLikesForPost(@PathVariable int postId) {
        List<Like> likes = postService.getLikesByPostId(postId);
        List<LikeDTO> likeDTOS = new ArrayList<>();
        for (Like like : likes) {
            likeDTOS.add(ConvertLikeEntityToDTO(like));
        }
        return likeDTOS;
    }

    @GetMapping("/post/{postId}/shares")
    public List<ShareDTO> getSharesForPost(@PathVariable int postId) {
        List<Share> shares = postService.getSharesByPostId(postId);
        List<ShareDTO> shareDTOS = new ArrayList<>();
        for (Share share : shares) {
            shareDTOS.add(ConvertShareEntityToDTO(share));
        }
        return shareDTOS;
    }

    @GetMapping("/post/{postId}/comments")
    public List<CommentDTO> getCommentsForPost(@PathVariable int postId) {
        List<Comment> Comments = postService.getCommentsByPostId(postId);
        List<CommentDTO> CommentDTOS = new ArrayList<>();
        for (Comment comment : Comments) {
            CommentDTOS.add(ConvertCommentEntityToDTO(comment));
        }
        return CommentDTOS;
    }

    private PostDTO ConvertPostEntityToDTO(Post post) {
        PostDTO postDTO = new PostDTO();
        postDTO.setId(post.getId());
        postDTO.setContent(post.getContent());
        postDTO.setPost_img(post.getPost_img());
        postDTO.setPost_video(post.getPost_video());
        return postDTO;
    }

    private LikeDTO ConvertLikeEntityToDTO(Like like) {
        LikeDTO likeDTO = new LikeDTO();
        likeDTO.setId(like.getId());
        likeDTO.setPostId(like.getPost().getId());
        return likeDTO;
    }

    private ShareDTO ConvertShareEntityToDTO(Share share) {
        ShareDTO ShareDTO = new ShareDTO();
        ShareDTO.setId(share.getId());
        ShareDTO.setPostId(share.getPost().getId());
        return ShareDTO;
    }

    private CommentDTO ConvertCommentEntityToDTO(Comment comment) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(comment.getId());
        commentDTO.setContent(comment.getContent());
        if (comment.getParent() != null) {
            commentDTO.setParentId(comment.getParent().getId());
        }
        commentDTO.setPostId(comment.getPost().getId());
        return commentDTO;
    }

}
