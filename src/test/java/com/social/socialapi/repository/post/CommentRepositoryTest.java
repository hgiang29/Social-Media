package com.social.socialapi.repository.post;

import com.social.socialapi.entity.User;
import com.social.socialapi.entity.post.Comment;
import com.social.socialapi.entity.post.Post;
import com.social.socialapi.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@DataJpaTest
@Rollback(value = false)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CommentRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    PostRepository postRepository;

    @Test
    void createComment() {
        User user = userRepository.findById(4);
        Post post = postRepository.findById(10).get();

        Comment comment = new Comment(user, "comment vao bai post nha", post);
        commentRepository.save(comment);
    }


    @Test
    void getCommentUserIdByPostId() {
        List<Integer> userIdList = commentRepository.getCommentUserIdByPostId(10);

        System.out.println(userIdList);
    }
}

