package com.technocorp.ericpinto.rentms.service;

import com.technocorp.ericpinto.rentms.model.User;
import com.technocorp.ericpinto.rentms.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    User user = User.builder()
            .id("5fc7ba0ee7e48d20dc2fbf52")
            .name("Éric")
            .email("eric@gmail.com").build();


    @Test
    @DisplayName("Should return all users")
    void WhenFindAll_thenTestReturn() {

        List<User> listUser = new ArrayList<>();
        listUser.add(user);

        Mockito.when(userRepository.findAll()).thenReturn(listUser);

        var response = userRepository.findAll();
        var stubExpect = listUser;

        assertEquals(stubExpect, response);
    }

}
