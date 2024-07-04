package com.social.socialapi.repository;

import com.social.socialapi.entity.User;
import com.social.socialapi.entity.enums.Gender;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@Rollback(value = false)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    void createUser() {
        User user = new User("Nguyen Van", "B", Gender.Female, "nguyenvanc@gmail.com", "abcde", "abc123");
        userRepository.save(user);
    }

    @Test
    void findUserById() {
        User user = userRepository.findById(3).
                orElseThrow(() -> new EntityNotFoundException(String.valueOf(1)));
        System.out.println(user);
    }

}
