package com.technocorp.ericpinto.rentms.service;

import com.technocorp.ericpinto.rentms.model.User;
import com.technocorp.ericpinto.rentms.repository.UserRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

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

    @Test
    public void whenFindAll_thenReturnProductList() {

        List<User> expectedUsers = new ArrayList<>();
        expectedUsers.add(this.user);

        when(userRepository.findAll()).thenReturn(expectedUsers);

        var response = userRepository.findAll();

        assertThat(response).isEqualTo(expectedUsers);
    }

    @Test
    public void whenFindById_thenReturnUser() {

        User user = User.builder()
                .id("5fc7ba0ee7e48d20dc2fbf52")
                .name("Éric Pinto")
                .email("ericgrandopinto@gmail.com")
                .build();

        when(userRepository.findById("5fc7ba0ee7e48d20dc2fbf52")).thenReturn(Optional.ofNullable(user));

        User obj = userService.findById("5fc7ba0ee7e48d20dc2fbf52");

        assertEquals("Éric Pinto", obj.getName());
        assertEquals("ericgrandopinto@gmail.com", obj.getEmail());
    }
}
