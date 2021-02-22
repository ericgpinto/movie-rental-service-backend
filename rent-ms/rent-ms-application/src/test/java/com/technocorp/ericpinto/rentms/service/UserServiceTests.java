package com.technocorp.ericpinto.rentms.service;

import com.technocorp.ericpinto.rentms.model.User;
import com.technocorp.ericpinto.rentms.repository.UserRepository;
import com.technocorp.ericpinto.rentms.service.exceptions.ObjectNotFoundException;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTests {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        user = User.builder()
                .id("5fc7ba0ee7e48d20dc2fbf52")
                .name("Éric Pinto")
                .email("ericgrandopinto@gmail.com")
                .build();
    }

//    @Test
//    public void whenCreate_thenReturnUser(){
//        assertThat(userService.create(user)).isEqualTo(user);
//    }
//
//    @Test
//    public void whenFindAll_thenReturnUserList() {
//
//        List<User> expectedUsers = new ArrayList<>();
//        expectedUsers.add(this.user);
//
//        when(userRepository.findAll()).thenReturn(expectedUsers);
//
//        var response = userRepository.findAll();
//
//        assertThat(response).isEqualTo(expectedUsers);
//    }

    @Test
    public void whenFindById_thenReturnUser() {

        when(userRepository.findById("5fc7ba0ee7e48d20dc2fbf52")).thenReturn(Optional.ofNullable(user));

        assertThat(userService.findById("5fc7ba0ee7e48d20dc2fbf52")).isEqualTo(user);
    }

    @Test
    public void shouldDelete(){

        User user = User.builder()
                .id("5fc7ba0ee7e48d20dc2fbf52")
                .name("Éric Pinto")
                .email("ericgrandopinto@gmail.com")
                .build();

        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        userService.delete(user.getId());
        verify(userRepository, times(1)).deleteById(user.getId());

    }

    @Test
    public void whenUpdate_thenReturnUser(){

        User user = User.builder()
                .id("5fc7ba0ee7e48d20dc2fbf52")
                .name("Éric Pinto")
                .email("ericgrandopinto@gmail.com")
                .build();

        when(userRepository.findById("5fc7ba0ee7e48d20dc2fbf52")).thenReturn(Optional.ofNullable(user));

        user.setName("Régis");
        user.setEmail("regis@gmail.com");
        when(userRepository.save(user)).thenReturn(user);

        assertThat(userService.update(user)).isEqualTo(user);

    }
}
