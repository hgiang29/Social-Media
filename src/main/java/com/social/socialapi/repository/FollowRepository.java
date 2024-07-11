package com.social.socialapi.repository;

import com.social.socialapi.entity.Follow;
import com.social.socialapi.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FollowRepository extends CrudRepository<Follow, Integer> {

    @Query("SELECT f.follower FROM Follow f WHERE f.user = ?1")
    List<User> getFollowerList(User user);

    @Query("SELECT f.user FROM Follow f WHERE f.follower = ?1")
    List<User> getFollowingList(User user);

    Follow getFollowByUserAndFollower(User user, User follower);

    Follow getFollowById(int id);

    boolean existsByUserAndFollower(User user, User follower);

    @Query(value = "SELECT u.id FROM users u left join follow f on u.id = f.user_id and f.follower_id = :userId WHERE f.user_id IS NULL AND u.id != :userId LIMIT 10",
            nativeQuery = true)
    List<Integer> getNotFollowUer(int userId);
}
