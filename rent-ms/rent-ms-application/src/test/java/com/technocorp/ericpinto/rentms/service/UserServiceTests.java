package com.technocorp.ericpinto.rentms.service;

import com.technocorp.ericpinto.rentms.model.User;
import com.technocorp.ericpinto.rentms.repository.UserRepository;
import com.technocorp.ericpinto.rentms.service.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class UserServiceTests {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    User user = User.builder()
            .id("5fc7ba0ee7e48d20dc2fbf52")
            .name("Éric")
            .email("eric@gmail.com").build();

    @Test
    @DisplayName("Should be create a user")
    void shouldCreateUser(){
        when(userRepository.insert(user)).thenReturn(user);

        var response = userService.create(user);
        var stubExpect = user;

        assertEquals(stubExpect, response);
    }

    @Test
    @DisplayName("Should be return all users")
    void shouldReturnAllUsers() {

        List<User> listUser = new ArrayList<>();
        listUser.add(user);

        when(userRepository.findAll()).thenReturn(listUser);

        listUser = userService.findAll();
        assertEquals(1, listUser.size(), "find all should return one user");
    }

    @Test
    @DisplayName("Test findById success")
    void shouldReturnAUser_whenFindById(){
        when(userRepository.findById("5fc7ba0ee7e48d20dc2fbf52")).thenReturn(Optional.of(user));

        var response = userService.findById("5fc7ba0ee7e48d20dc2fbf52");
        var stubExpect = user;

        assertEquals(stubExpect, response);

    }

    @Test
    @DisplayName("ShoUld be return a exception when find by id")
    void shouldReturnAException_whenFindById(){
        assertThrows(ObjectNotFoundException.class, () -> userService.findById("5fc7ba0ee7e48d20dc2fbf52"));
    }

    @Test
    @DisplayName("Shold be update a user")
    void shouldUpdateUser(){
        User newUser = User.builder()
                .id("5fc7ba0ee7e48d20dc2fbf52")
                .name("Éric Pinto")
                .email("ericpinto@gmail.com").build();

        when(userRepository.findById("5fc7ba0ee7e48d20dc2fbf52")).thenReturn(Optional.ofNullable(user));
        when(userRepository.save(user)).thenReturn(user);

        var stubExpect = user;
        var response = userService.udpated("5fc7ba0ee7e48d20dc2fbf52", newUser);

        assertEquals(stubExpect, response);
    }

    @Test
    @DisplayName("Shold be delete a user")
    void shouldDeleteUser(){
        when(userRepository.findById("5fc7ba0ee7e48d20dc2fbf52")).thenReturn(Optional.ofNullable(user));
        userService.delete(user.getId());
        verify(userRepository, times(1)).deleteById(user.getId());
    }


}
