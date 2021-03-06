package com.technocorp.ericpinto.rentms.EndpointTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.technocorp.ericpinto.rentms.controller.UserController;
import com.technocorp.ericpinto.rentms.model.User;
import com.technocorp.ericpinto.rentms.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UserController.class)
@AutoConfigureRestDocs(outputDir = "build/snippets")
class UserEndpointsTests {

    @MockBean
    private UserService userService;

    @Autowired
    MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    User user = User.builder()
            .id("5fc7ba0ee7e48d20dc2fbf52")
            .name("Éric")
            .email("eric@gmail.com")
            .mobileNumber("51992901094")
            .build();

    @Test
    @DisplayName("Should be save a user")
    void shouldReturn201_WhenSaveAUser() throws Exception {
        when(userService.create(user)).thenReturn(user);

        this.mockMvc.perform(post("/rentapi/users")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isCreated())
                .andDo(document("{methodName}",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())));
    }

    @Test
    @DisplayName("Should be return all users")
    void findAllShouldReturnListOfUsers() throws Exception {

        List<User> listUsers = new ArrayList<>();
        listUsers.add(user);

        when(userService.findAll()).thenReturn(listUsers);

        mockMvc.perform(get("/rentapi/users").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(listUsers)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andDo(document("{methodName}",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())));
    }

    @Test
    @DisplayName("Should be return a user by id")
    void shouldReturn200_WhenFindUserById() throws Exception {
        when(userService.findById("5fc7ba0ee7e48d20dc2fbf52")).thenReturn(user);

        this.mockMvc.perform(get("/rentapi/users/5fc7ba0ee7e48d20dc2fbf52")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user))
        ).andExpect(status().isOk())
                .andDo(document("{methodName}",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint())));
    }

    @Test
    @DisplayName("Should be return a user by email")
    void shouldReturn200_WhenFindUserByEmail() throws Exception {
        when(userService.findByEmail(user.getEmail())).thenReturn(user);

        this.mockMvc.perform(get("/rentapi/users/emailsearch?email=ericgrandopinto@gmail.com")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user))
        ).andExpect(status().isOk())
                .andDo(document("{methodName}",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())));
    }

    @Test
    @DisplayName("Should be return a user by mobile number")
    void shouldReturn200_WhenFindUserByMobileNumber() throws Exception {
        when(userService.findByMobileNumber(user.getMobileNumber())).thenReturn(user);

        this.mockMvc.perform(get("/rentapi/users/mobile-number-search?mobilenumber=51992901094")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user))
        ).andExpect(status().isOk())
                .andDo(document("{methodName}",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())));
    }

    @Test
    @DisplayName("Should be update a user")
    void shouldReturn200_WhenUpdateUser() throws Exception {

        User newUser = User.builder()
                .id("5fc7ba0ee7e48d20dc2fbf52")
                .name("Éric Pinto")
                .mobileNumber("51992901094")
                .email("ericpinto@gmail.com").build();

        when(userService.findById("5fc7ba0ee7e48d20dc2fbf52")).thenReturn(user);
        when(userService.udpated("5fc7ba0ee7e48d20dc2fbf52", newUser)).thenReturn(user);

        this.mockMvc.perform(put("/rentapi/users/5fc7ba0ee7e48d20dc2fbf52")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andDo(document("{methodName}",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())));
    }

    @Test
    @DisplayName("Should be delete a user")
    void shouldReturn204_WhenDeleteUser() throws Exception {
        this.mockMvc.perform(delete("/rentapi/users/5fc7ba0ee7e48d20dc2fbf52"))
                .andExpect(status().isNoContent())
                .andDo(document("{methodName}",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint())));
    }

}