package com.technocorp.ericpinto.rentms;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.technocorp.ericpinto.rentms.controller.UserController;
import com.technocorp.ericpinto.rentms.model.User;
import com.technocorp.ericpinto.rentms.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ContextConfiguration
@AutoConfigureMockMvc
class UserIntegrationTests {

    private final String BASE_URL = "/rentapi/users";

    @MockBean
    UserRepository userRepository;

    @Autowired
    MockMvc mockMvc;

    User user = User.builder().id("5fc7ba0ee7e48d20dc2fbf52").name("Éric").email("eric@gmail.com")
            .build();

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("Should return status 200 when find all users")
    void listUsers_shouldReturnStatusCode200() throws Exception {
        List<User> listUsers = new ArrayList<>();
        listUsers.add(user);

        when(userRepository.findAll()).thenReturn(listUsers);

        mockMvc.perform(get(BASE_URL)
                .accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Should return status 200 when find by id")
    void findUserById_shouldReturnStatusCode200() throws Exception {

        when(userRepository.findById("5fc7ba0ee7e48d20dc2fbf52")).thenReturn(Optional.of(user));

        mockMvc.perform(get(BASE_URL + "/5fc7ba0ee7e48d20dc2fbf52")
                .accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(user.getId())))
                .andExpect(jsonPath("$.name", is(user.getName())))
                .andExpect(jsonPath("$.email", is(user.getEmail())));

        verify(userRepository, times(1)).findById(user.getId());
    }

    @Test
    @DisplayName("Should return status 404 when user not found")
    void findUserById_shouldReturnStatusCode404_WhenUserNotFound() throws Exception {
        mockMvc.perform(get(BASE_URL + "/1")
                .accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Should return status 201 when user created")
    void saveUser_shouldReturnStatusCode201() throws Exception {

        when(userRepository.insert(user)).thenReturn(user);

        mockMvc.perform(post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user))
                .accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(user.getId())))
                .andExpect(jsonPath("$.name", is(user.getName())))
                .andExpect(jsonPath("$.email", is(user.getEmail())));

        verify(userRepository, times(1)).insert(any(User.class));
    }

    @Test
    @DisplayName("Should return status 200 when user updated")
    void updateUser_shouldReturnStatusCode200() throws Exception {

        when(userRepository.findById("5fc7ba0ee7e48d20dc2fbf52")).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);

        mockMvc.perform(put(BASE_URL + "/5fc7ba0ee7e48d20dc2fbf52")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user))
                .accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(user.getId())));

    }

    @Test
    @DisplayName("Should return status 204 when user deleted")
    void deleteUser_shouldReturnStatusCode204() throws Exception {
        when(userRepository.findById("5fc7ba0ee7e48d20dc2fbf52")).thenReturn(Optional.ofNullable(user));
        this.mockMvc.perform(delete(BASE_URL + "/5fc7ba0ee7e48d20dc2fbf52"))
                .andExpect(status().isNoContent());

        verify(userRepository, times(1)).deleteById("5fc7ba0ee7e48d20dc2fbf52");
    }

}
