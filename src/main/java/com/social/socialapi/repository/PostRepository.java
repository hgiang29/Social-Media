package com.social.socialapi.repository;

import com.social.socialapi.domain.entity.User;
import com.social.socialapi.domain.entity.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

public interface PostRepository extends CrudRepository<Post, Integer> {

}
