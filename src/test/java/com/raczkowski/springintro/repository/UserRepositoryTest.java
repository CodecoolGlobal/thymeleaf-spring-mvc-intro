package com.raczkowski.springintro.repository;

import com.raczkowski.springintro.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void should_save_user() {
        // given:
        User user = new User("Przemek", "Raczkowski");

        // when
        userRepository.save(user);
    }

    @Test
    @Sql("populateUsers.sql")
    public void should_return_user_by_id() {
        // when:
        Optional<User> maybeUser = userRepository.findById(1L);

        // then:
        assertTrue(maybeUser.isPresent());
    }

}
