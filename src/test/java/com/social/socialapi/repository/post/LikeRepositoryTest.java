package com.social.socialapi.repository.post;

import com.social.socialapi.entity.post.Like;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class LikeRepositoryTest {

    public LikeRepository likeRepository;
    @Autowired
    public LikeRepositoryTest(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }
    @Test
    public void Test_LikeByComment(){
        int commentId = 1;
        List<Like> likes = likeRepository.getLikesByComment(commentId);
        Assertions.assertThat(likes).isNotEmpty();
    }

}