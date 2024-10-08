package com.social.socialapi.controller.post;

import com.social.socialapi.dto.request.*;
import com.social.socialapi.dto.response.PostErrorReponse;
import com.social.socialapi.entity.post.Comment;
import com.social.socialapi.entity.post.Like;
import com.social.socialapi.entity.post.Post;
import com.social.socialapi.entity.post.Share;
import com.social.socialapi.exceptions.PostNotFoundException;
import com.social.socialapi.service.LikeService;
import com.social.socialapi.service.PostService;
import com.social.socialapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@CrossOrigin
public class PostController {

    @Autowired
    private PostService postService;
    @Autowired
    private LikeService likeService;
    @Autowired
    private UserService userService;

    @GetMapping("/posts")
    public ResponseEntity<List<PostDTO>> getAllPost() {
        // cách để chuyển từ List<Post> sang List<PostDTO>
        List<Post> posts = postService.getAllPosts();
        List<PostDTO> postDTOs = new ArrayList<>();
        for (Post post : posts) {
            postDTOs.add(post.ConvertPostToPostDTO());
        }
        for (PostDTO postDTO : postDTOs) {
            List<LikeDTO> likeDTOList = getLikesForPost(postDTO.getId());
            postDTO.setLikeDTOs(likeDTOList.size());
            postDTO.setListLikes(likeDTOList);
            List<ShareDTO> shareDTOList = getSharesForPost(postDTO.getId());
            postDTO.setShareDTOS(shareDTOList.size());
            List<CommentDTO> commentDTOList = getCommentsForPost(postDTO.getId());
            postDTO.setCommentDTOS(commentDTOList.size());
        }
        return ResponseEntity.ok(postDTOs);
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<PostDTO> getPost(@PathVariable int postId) {
        Post post = new Post();
        try {
            post = postService.getPostById(postId);
        } catch (Exception e) {
             throw new PostNotFoundException("Post id not found: "+ postId);
        }
        PostDTO postDTO = post.ConvertPostToPostDTO();
        List<LikeDTO> likeDTOList = getLikesForPost(postDTO.getId());
        postDTO.setLikeDTOs(likeDTOList.size());
        postDTO.setListLikes(likeDTOList);
        List<ShareDTO> shareDTOList = getSharesForPost(postDTO.getId());
        postDTO.setShareDTOS(shareDTOList.size());
        List<CommentDTO> commentDTOList = getCommentsForPost(postDTO.getId());
        postDTO.setCommentDTOS(commentDTOList.size());
        return ResponseEntity.ok(postDTO);
    }

//        @GetMapping("jobPosts/keyword/{keyword}")
//        public List<JobPost> searchByKeyword(@PathVariable("keyword") String keyword){
//            return service.search(keyword);
//
//        }

    //    @PostMapping("/post")
//    public ResponseEntity<PostDTO> addPost(String content, Integer userId,  final List<MultipartFile> files) {
//        PostDTO ResponsePostDTO = postService.addPost(content,userId, files);
//        return ResponseEntity.ok(ResponsePostDTO);
//    }
    @PostMapping("/post")
    public ResponseEntity<PostDTO> addPost(@RequestBody AddingPostDTO addingPost) {
        List<MultipartFile> files = new ArrayList<MultipartFile>();
        for (String fileString : addingPost.fileString) {
            fileString = fileString.substring(fileString.indexOf(",") + 1);
            files.add(addingPost.convert(fileString, "image.jpg"));
        }
        PostDTO ResponsePostDTO = postService.addPost(addingPost.content, addingPost.userId, files);
        return ResponseEntity.ok(ResponsePostDTO);
    }


    @PostMapping("/post/image/{PostId}")
    public ResponseEntity<PostDTO> addImage(@PathVariable final Integer PostId, @RequestPart final MultipartFile file) {
        this.postService.uploadImage(PostId, file);
        return ResponseEntity.ok(postService.getPostById(PostId).ConvertPostToPostDTO());
    }

    @PutMapping("/post")
    public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO postDTO) {
        postService.updatePost(postDTO);
        PostDTO ResponsePostDTO = postService.getPostById(postDTO.getId()).ConvertPostToPostDTO();
        return ResponseEntity.ok(ResponsePostDTO);
    }

    @DeleteMapping("post/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable int postId) {
//        Post post = postService.getPostById(postId);
//        post.setUser(null);
//        postRepository.save(post);
        postService.deletePost(postId);

        return ResponseEntity.ok("Deleted");
    }

    @GetMapping("/posts/{userId}")
    public ResponseEntity<List<PostDTO>> getAllPost(@PathVariable int userId) {
        // cách để chuyển từ List<Post> sang List<PostDTO>
        List<PostDTO> postDTOS = postService.getAllPostsByUserId(userId).stream().map(Post::ConvertPostToPostDTO).collect(Collectors.toList());
        for (PostDTO postDTO : postDTOS) {
            List<LikeDTO> likeDTOList = getLikesForPost(postDTO.getId());
            postDTO.setLikeDTOs(likeDTOList.size());
            postDTO.setListLikes(likeDTOList);

            List<ShareDTO> shareDTOList = getSharesForPost(postDTO.getId());
            postDTO.setShareDTOS(shareDTOList.size());
            List<CommentDTO> commentDTOList = getCommentsForPost(postDTO.getId());
            postDTO.setCommentDTOS(commentDTOList.size());
        }
        return ResponseEntity.ok(postDTOS);
    }

    // region: get Like, share , comment of Posts
    @GetMapping("/post/{postId}/likes")
    public List<LikeDTO> getLikesForPost(@PathVariable int postId) {
        List<Like> likes = postService.getLikesByPostId(postId);
        List<LikeDTO> likeDTOS = new ArrayList<>();
        for (Like like : likes) {
            likeDTOS.add(like.ConvertLikeEntityToDTO());
        }
        return likeDTOS;
    }

    @GetMapping("/post/{postId}/shares")
    public List<ShareDTO> getSharesForPost(@PathVariable int postId) {
        List<Share> shares = postService.getSharesByPostId(postId);
        List<ShareDTO> shareDTOS = new ArrayList<>();
        for (Share share : shares) {
            shareDTOS.add(share.ConvertShareEntityToDTO());
        }
        return shareDTOS;
    }

    @GetMapping("/post/{postId}/comments")
    public List<CommentDTO> getCommentsForPost(@PathVariable int postId) {
        List<Comment> Comments =  null;
        try {
            Comments = postService.getCommentsByPostId(postId);
        }
        catch (Exception e){
            throw new PostNotFoundException(e);
        }
        List<CommentDTO> CommentDTOS = new ArrayList<>();
        for (Comment comment : Comments) {
            List<Like> likes = likeService.getLikesByComment(comment.getId());
            CommentDTO commentDTO = comment.ConvertCommentEntityToDTO();
            commentDTO.setLikes(likes.size());
            CommentDTOS.add(commentDTO);
        }
        return CommentDTOS;
    }

}

