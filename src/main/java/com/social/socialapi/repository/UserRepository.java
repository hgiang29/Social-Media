package com.social.socialapi.repository;

import com.social.socialapi.entity.user.User;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findByEmail(String email);

    User findById(int id);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u WHERE u.username = :username")
    boolean existsByUsername(@Param("username") String username);

    @Query("SELECT u FROM User u where u.id != :userId")
    List<User> listAllUserExceptMe(@Param("userId") int userId);

    User findByUsername(String username);

    //@Query("SELECT u FROM User u WHERE u.username LIKE %:username% LIMIT 10")
    //List<User> findByUsernameContains(@Param("username") String username);

}
