package com.technocorp.ericpinto.rentms.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.technocorp.ericpinto.rentms.model.User;
import com.technocorp.ericpinto.rentms.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTests {

    @Mock
    UserService userService;

    @InjectMocks
    UserController userController;

    ObjectMapper objectMapper = new ObjectMapper();

    User user = User.builder()
            .id("5fc7ba0ee7e48d20dc2fbf52")
            .name("Éric")
            .mobileNumber("51992901094")
            .email("eric@gmail.com").build();

    @Test
    @DisplayName("Should return a list of users")
    void whenFindAllshouldReturnAListOfUsers() {

        List<User> listUsers = new ArrayList<>();
        listUsers.add(user);

        when(userService.findAll()).thenReturn(listUsers);
        var stubActual = userController.findAll();

        assertEquals(listUsers, stubActual);
    }

    @Test
    @DisplayName("Should return a user")
    void whenFindById_shouldReturnTheUserFounded() {

        when(userService.findById(user.getId())).thenReturn(user);
        var stubActual = userController.findById(user.getId());

        assertEquals(user, stubActual);
    }

    @Test
    @DisplayName("Should return a user by email")
    void whenFindByEmail_shouldReturnTheUserFounded() {

        when(userService.findByEmail(user.getEmail())).thenReturn(user);
        var stubActual = userController.findByEmail(user.getEmail());

        assertEquals(user, stubActual);
    }

    @Test
    @DisplayName("Should return a user by mobile number")
    void whenFindByMobileNumber_shouldReturnTheUserFounded() {

        when(userService.findByMobileNumber(user.getMobileNumber())).thenReturn(user);
        var stubActual = userController.findByMobileNumber(user.getMobileNumber());

        assertEquals(user, stubActual);
    }

    @Test
    @DisplayName("Should return the saved user")
    void whenCreate_shouldReturnTheUserCreated() {
        when(userService.create(user)).thenReturn(user);
        var stubActual = userController.create(user);
        assertEquals(user, stubActual);
    }

    @Test
    @DisplayName("Should return the updated user")
    void whenUpdateShouldReturnTheUserUpdated() {

        when(userService.udpated(user.getId(), user)).thenReturn(user);

        var stubActual = userController.update(user.getId(), user);

        assertEquals(user, stubActual);
    }

    @Test
    @DisplayName("Should delete many users")
    void shouldReturnNothing_whenDeleteManyUser(){
        List<String> ids;
        ids = Collections.singletonList(user.getId());
        userController.deleteMany(ids);

        verify(userService, times(1)).delete(ids.get(0));
    }

    @Test
    @DisplayName("Should delete a user")
    void shouldDeleteAUser(){
        userController.delete(user.getId());
        verify(userService, times(1)).delete(user.getId());
    }
}
