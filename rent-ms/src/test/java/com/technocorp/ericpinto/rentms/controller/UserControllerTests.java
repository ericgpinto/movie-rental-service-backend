package com.technocorp.ericpinto.rentms.controller;

import com.technocorp.ericpinto.rentms.UserController;
import com.technocorp.ericpinto.rentms.model.User;

import com.technocorp.ericpinto.rentms.service.UserService;
import static org.assertj.core.api.Assertions.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.*;

import org.mockito.ArgumentCaptor;
import org.mockito.exceptions.base.MockitoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = UserController.class)

public class UserControllerTests {
    List<User> users = new ArrayList<>();

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserService userService;

    @Test
    public void findAllShouldReturnListOfUsers() throws Exception {
        User firstUser = User.builder()
                .id("5fc7ba0ee7e48d20dc2fbf52")
                .name("Éric")
                .email("eric@gmail.com").build();

        users.add(firstUser);

        when(userService.findAll()).thenReturn(users);

        mockMvc.perform(get("/users").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id", is("5fc7ba0ee7e48d20dc2fbf52")))
                .andExpect(jsonPath("$.[0].name", is("Éric")))
                .andExpect(jsonPath("$.[0].email", is("eric@gmail.com")));
    }

    @Test
    public void shouldReturnUser_WhenSave() throws Exception {
        User firstUser = User.builder()
                .id("5fc7ba0ee7e48d20dc2fbf52")
                .name("Éric")
                .email("eric@gmail.com").build();

        users.add(firstUser);

        when(userService.create(any())).thenReturn(firstUser);
        mockMvc.perform(post("/users")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Éric\",\"email\":\"eric@gmail.com\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is("5fc7ba0ee7e48d20dc2fbf52")))
                .andExpect(jsonPath("$.name", is("Éric")))
                .andExpect(jsonPath("$.email", is("eric@gmail.com")));
        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        verify(userService).create(captor.capture());
        assertThat(captor.getValue().getId()).isNull();
        assertThat(captor.getValue().getName()).isEqualTo("Éric");
        assertThat(captor.getValue().getEmail()).isEqualTo("eric@gmail.com");
    }

    @Test
    public void shouldReturnUser_WhenFindById() throws Exception {
        User firstUser = User.builder()
                .id("5fc7ba0ee7e48d20dc2fbf52")
                .name("Éric")
                .email("eric@gmail.com").build();

        users.add(firstUser);

        when(userService.findById("5fc7ba0ee7e48d20dc2fbf52")).thenReturn(firstUser);
        mockMvc.perform(get("/users/{id}", "5fc7ba0ee7e48d20dc2fbf52").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is("5fc7ba0ee7e48d20dc2fbf52")))
                .andExpect(jsonPath("$.name", is("Éric")))
                .andExpect(jsonPath("$.email", is("eric@gmail.com")));
    }


}
