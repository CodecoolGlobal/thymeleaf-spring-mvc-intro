package com.raczkowski.springintro.service;

import com.raczkowski.springintro.exception.UserNotFoundException;
import com.raczkowski.springintro.entity.User;
import com.raczkowski.springintro.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void should_return_all_users() {
        // given:
        List<User> expectedResult = createUsersSamples();
        when(userRepository.findAll()).thenReturn(expectedResult);

        // when:
        List<User> results = userService.getAllUsers();

        // then:
        assertEquals(results.size(), expectedResult.size());
        assertTrue(results.containsAll(expectedResult));
    }

    @Test
    public void should_return_users_that_match_pattern() {
        // given:
        String prefix = "Prz";
        List<User> expectedResult = singletonList(new User("Przemek", "Address"));
        when(userRepository.findAll()).thenReturn(createUsersSamples());

        // when:
        List<User> results = userService.getUsersThatNamesStartsFrom(prefix);

        // then:
        assertEquals(results.size(), expectedResult.size());
        assertTrue(results.containsAll(expectedResult));
    }

    @Test
    public void should_return_empty_list_when_no_users_match_pattern() {
        // given:
        String prefix = "Wrrr";
        when(userRepository.findAll()).thenReturn(createUsersSamples());

        // when:
        List<User> results = userService.getUsersThatNamesStartsFrom(prefix);

        // then:
        assertEquals(0, results.size());
    }

    @Test
    public void should_delete_user() {
        // given:
        Long id = 1L;

        // when:
        userService.deleteUser(id);

        // then:
        verify(userRepository, times(1)).deleteById(eq(id));
    }

    @Test(expected = UserNotFoundException.class)
    public void should_throw_exception_when_user_not_found_while_deleting() {
        // given:
        Long id = 345345L;
        doThrow(UserNotFoundException.class).when(userRepository).deleteById(id);

        // when:
        userService.deleteUser(id);
    }

    @Test
    public void should_throw_exception_when_user_not_found_while_deleting_assertJ_version() {
        // given:
        Long id = 345345L;
        String exceptionMessage = "User nor found for given id: " + id;
        doThrow(new UserNotFoundException(id)).when(userRepository).deleteById(id);

        // when:
        Throwable exception = catchThrowable(() -> userService.deleteUser(id));

        // then:
        assertThat(exception)
                .isInstanceOf(UserNotFoundException.class)
                .hasMessageContaining(exceptionMessage);
    }

    private List<User> createUsersSamples() {
        return Arrays.asList(
                new User("Przemek", "Address"),
                new User("Romek", "Address2")
        );
    }
}