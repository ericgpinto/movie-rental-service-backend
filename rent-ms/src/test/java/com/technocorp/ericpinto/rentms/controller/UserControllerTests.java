package com.technocorp.ericpinto.rentms.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.technocorp.ericpinto.rentms.UserController;
import com.technocorp.ericpinto.rentms.model.User;

import com.technocorp.ericpinto.rentms.service.UserService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.exceptions.base.MockitoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;
import java.util.List;

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

        Mockito.when(userService.findAll()).thenReturn(users);

        mockMvc.perform(get("/users").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id", is("5fc7ba0ee7e48d20dc2fbf52")))
                .andExpect(jsonPath("$.[0].name", is("Éric")))
                .andExpect(jsonPath("$.[0].email", is("eric@gmail.com")));
    }

}
