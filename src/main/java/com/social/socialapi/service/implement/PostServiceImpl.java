package com.social.socialapi.service.implement;

import com.social.socialapi.dto.inputdto.PostDTO;
import com.social.socialapi.dto.outputdto.CloudinaryResponseDTO;
import com.social.socialapi.entity.User;
import com.social.socialapi.entity.post.Comment;
import com.social.socialapi.entity.post.Like;
import com.social.socialapi.entity.post.Post;
import com.social.socialapi.entity.post.Share;
import com.social.socialapi.exceptions.FuncErrorException;
import com.social.socialapi.repository.post.CommentRepository;
import com.social.socialapi.repository.post.LikeRepository;
import com.social.socialapi.repository.post.PostRepository;
import com.social.socialapi.repository.UserRepository;
import com.social.socialapi.repository.post.ShareRepository;
import com.social.socialapi.service.FileUploadService;
import com.social.socialapi.service.PostService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    public FileUploadService fileUploadService;
    @Autowired
    public PostRepository postRepository;
    @Autowired
    public UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private LikeRepository likeRepository;
    @Autowired
    private ShareRepository shareRepository;

    public List<Post> getAllPosts() {
        try {

            return (List<Post>) postRepository.findAll();
        } catch (Exception e) {
            System.out.println("Error occurred while fetching posts: " + e);
            return new ArrayList<>();
        }
    }
    public List<Post> getAllPostsByUserId(int UserId){
        return (List<Post>) postRepository.findAllByUserId(UserId);
    }

    public PostDTO addPost(String content, Integer userId, final List<MultipartFile> files) {
        User user = userRepository.findById(userId).get();
        Post post = new Post();
        post.setContent(content);
        post.setUser(user);
        post.setCreatedAt(Date.from(Instant.now()));
        post.setUpdateAt(Date.from(Instant.now()));
        String postImg = "";
        if(files != null ) {
            for (MultipartFile file : files) {
                String fileName = file.getOriginalFilename();
                final CloudinaryResponseDTO responseDTO = this.fileUploadService.uploadFile(file, fileName);
                postImg += responseDTO.getUrl() + ";";
            }
        }
        post.setPost_img(postImg);
        return postRepository.save(post).ConvertPostToPostDTO();
    }
    public PostDTO addPost(String content, Integer userId) {
        User user = userRepository.findById(userId).get();
        Post post = new Post();
        post.setContent(content);
        post.setUser(user);
        post.setCreatedAt(Date.from(Instant.now()));
        post.setUpdateAt(Date.from(Instant.now()));
        String postImg = "";
//        if(files != null ) {
//            for (MultipartFile file : files) {
//                String fileName = file.getOriginalFilename();
//                final CloudinaryResponseDTO responseDTO = this.fileUploadService.uploadFile(file, fileName);
//                postImg += responseDTO.getUrl() + ";";
//            }
//        }
        post.setPost_img(postImg);
        return postRepository.save(post).ConvertPostToPostDTO();
    }
    public Post getPostById(int id) {
        return postRepository.findById(id).orElse(new Post());
    }

    public void updatePost(PostDTO postDTO) {
        Post post = postDTO.ConvertDTOtoEntity();
        post.setUpdateAt(Date.from(Instant.now()));
        postRepository.save(post);
    }

    @Transactional
    public void uploadImage(final Integer id, final MultipartFile file) {
        final Post post = this.postRepository.findById(id).orElseThrow(() -> new FuncErrorException("Post not found"));
//        FileUploadUtil.assertAllowed(file, FileUploadUtil.IMAGE_PATTERN);
        String fileName = file.getOriginalFilename();
        final CloudinaryResponseDTO responseDTO = this.fileUploadService.uploadFile(file, fileName);
        post.setPost_img(responseDTO.getUrl());
        this.postRepository.save(post);
    }

    public void deletePost(int postId) {
        commentRepository.deleteByPostId(postId);
        likeRepository.deleteByPostId(postId);
        shareRepository.deleteByPostId(postId);
        postRepository.deleteById(postId);
    }

    //    public void addLikePost(Like like) {
//        postRepository.addLikePost(like);
//    }
    public List<Like> getLikesByPostId(int postId) {
        return postRepository.showLikePost(postId);
    }

    public List<Share> getSharesByPostId(int postId)
    {
        return postRepository.showSharePost(postId);
    }

    public List<Comment> getCommentsByPostId(int postId)
    {
        return postRepository.showCommentPost(postId);
    }
    //    public List<Post> search(String keyword) {
//
//        return postRepository.findByPostProfileContainingOrPostDescContaining(keyword,keyword);
//    }
//    public Post ConvertPostDTOtoEntity(PostDTO postDTO) {
//        Post post = new Post();
//        User user = userRepository.findById(postDTO.getPostUserId()).orElse(new User());;
//        post.setId(postDTO.getId());
//        post.setUser(user);
//        post.setContent(postDTO.getContent());
//        post.setPost_img(postDTO.getPost_img());
//        post.setPost_video(postDTO.getPost_video());
//        return post;
//    }
}
