package com.raczkowski.springintro.controller;


import com.raczkowski.springintro.dto.UserDto;
import com.raczkowski.springintro.entity.User;
import com.raczkowski.springintro.TestConfiguration;
import com.raczkowski.springintro.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfiguration.class})
@WebAppConfiguration
public class UserControllerIntegrationTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private UserService userService;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void should_return_all_users() throws Exception {
        // given:
        List<User> users = createUsersSamples();
        when(userService.getAllUsers()).thenReturn(users);

        // when, then:
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(view().name("allUsers"))
                .andExpect(model().attributeExists("users"))
                .andExpect(model().attribute("users", hasSize(2)))
                .andExpect(model().attribute("users", hasItem(
                        allOf(
                                hasProperty("id", is(1L)),
                                hasProperty("name", is("Przemek")),
                                hasProperty("address", is("Kraków"))
                        )
                )))
                .andExpect(model().attribute("users", hasItem(
                        allOf(
                                hasProperty("id", is(2L)),
                                hasProperty("name", is("Romek")),
                                hasProperty("address", is("Lublin"))
                        )
                )));

//                .andExpect(model().attribute("users", expectedUsersDto));
    }

    @Test
    public void should_return_all_users_form() throws Exception {
        mockMvc.perform(get("/users/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("addUsersForm"))
                .andExpect(model().attributeExists("user"))
                .andExpect(model().attribute("user", allOf(
                        hasProperty("id", is(nullValue())),
                        hasProperty("name", is(nullValue())),
                        hasProperty("address", is(nullValue()))
                )));
    }

    @Test
    public void should_persist_user() throws Exception {
        // given:
        String name = "Czesiu";
        String address = "Poznań";

        // when, then:
        mockMvc.perform(
                post("/users")
                        .param("name", name)
                        .param("address", address)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("users"));

        verify(userService, times(1)).addUser(new User(name, address));
    }

    @Test
    public void should_delete_user() throws Exception {
        // given:
        Long id = 1L;

        mockMvc.perform(get("/users/delete/{id}", id))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users"));

        verify(userService, times(1)).deleteUser(id);
    }

    private List<User> createUsersSamples() {
        return Arrays.asList(
                new User(1L, "Przemek", "Kraków"),
                new User(2L, "Romek", "Lublin")
        );
    }

    private List<UserDto> convertToUserDto(List<User> users) {
        return users.stream()
                .map(user -> new UserDto(user.getName(), user.getAddress()))
                .collect(toList());
    }
}
