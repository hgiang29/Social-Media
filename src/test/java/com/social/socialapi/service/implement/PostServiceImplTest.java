package com.social.socialapi.service.implement;

import com.social.socialapi.dto.request.PostDTO;
import com.social.socialapi.entity.post.Post;
import com.social.socialapi.entity.user.User;
import com.social.socialapi.events.PostAddedEvent;
import com.social.socialapi.repository.UserRepository;
import com.social.socialapi.repository.post.PostRepository;
import org.assertj.core.api.Assert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PostServiceImplTest {

    @InjectMocks
    private PostServiceImpl postService;
    @Mock
    private PostRepository postRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private ApplicationEventPublisher eventPublisher;
    @BeforeEach
    void setUp() {
    }
    @Test
    void shouldGetAllPosts() {
        List<Post> posts = new ArrayList<>();
        posts.add(new Post("hieu 1"));
        posts.add(new Post("hieu 2"));
        when(postRepository.findAll()).thenReturn(posts);
        List<Post> result = postService.getAllPosts();
        for(Post post : result) {
            System.out.println(post.getContent()+ " ");
        }
        Assertions.assertThat(result).hasSize(2);
    }

    @Test
    void getAllPostsByUserId() {
        int userId = 1;
        User user = new User();
        user.setId(userId);
        user.setFirstName("hieu");
        List<Post> posts = new ArrayList<>();
        posts.add(new Post("hieu 1", user));
        posts.add(new Post("hieu 2", user));
        when(postRepository.findAllByUserId(userId)).thenReturn(posts);
        List<Post> result = postService.getAllPostsByUserId(userId);
        System.out.println(posts);
        Assertions.assertThat(result).hasSize(2);
    }
    @Test
    void testCreatePost(){
        Integer userId = 1;
        User user = new User();
        user.setId(userId);
        user.setFirstName("hieu");
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        PostDTO postDTO = postService.addPost("post",1);
        Assertions.assertThat(postDTO).isNotNull();

    }

}