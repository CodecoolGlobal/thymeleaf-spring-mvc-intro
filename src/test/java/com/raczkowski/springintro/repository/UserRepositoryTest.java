package com.raczkowski.springintro.repository;

import com.raczkowski.springintro.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@Sql("populate_users.sql")
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void should_save_user() {
        // given:
        User user = new User("Przemek", "Raczkowski");

        // when
        User saved = userRepository.save(user);

        Optional<User> maybeSavedUser = userRepository.findById(saved.getId());

        assertTrue(maybeSavedUser.isPresent());
    }

    @Test
    public void should_return_user_by_id() {
        // when:
        Optional<User> maybeUser = userRepository.findById(1L);

        // then:
        assertTrue(maybeUser.isPresent());
    }

    @Test
    public void should_return_all_users() {
        // given:
        int numberOfPersistedUsers = 5;

        // when:
        List<User> users = (List<User>) userRepository.findAll();

        // then:
        assertEquals(users.size(), numberOfPersistedUsers);
    }

    @Test
    public void should_return_all_users_matching_name_prefix() {
        // given:
        String prefix = "Sł";
        int expectedMatchedUsersSize = 2;

        String firstMatchedUserName = "Sławomir";
        String secondMatchedUserName = "Sławek";

        // when:
        List<User> matchedUsers = userRepository.findByNameStartingWith(prefix);

        // then:
        assertEquals(expectedMatchedUsersSize, matchedUsers.size());
        assertEquals(matchedUsers.get(0).getName(), firstMatchedUserName);
        assertEquals(matchedUsers.get(1).getName(), secondMatchedUserName);
    }
}
