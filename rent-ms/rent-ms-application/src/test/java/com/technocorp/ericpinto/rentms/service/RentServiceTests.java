package com.technocorp.ericpinto.rentms.service;

import com.technocorp.ericpinto.rentms.model.Film;
import com.technocorp.ericpinto.rentms.model.Rent;
import com.technocorp.ericpinto.rentms.model.User;
import com.technocorp.ericpinto.rentms.repository.RentRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class RentServiceTests {

    @Mock
    RentRepository rentRepository;

    @Mock
    FilmService filmService;

    @InjectMocks
    RentService rentService;

    User user = User.builder().id("5fc7ba0ee7e48d20dc2fbf52").name("Éric").email("eric@gmail.com")
            .mobileNumber("51992901094").build();

    Film film = Film.builder().title("Revenge of the Sith").episodeId(6).director("George Lucas").build();

    Rent rent = Rent.builder()
            .id("5fc7ba0ee7e48d20dc2fbf52").user(user).film(film).initialDate(LocalDateTime.now())
            .finalDate(LocalDateTime.now().plusDays(2))
            .build();

    @Test
    @DisplayName("Should be return all Rents")
    void shouldReturnAllRents(){
        List<Rent> listRents = new ArrayList<>();
        listRents.add(rent);

        when(rentRepository.findAll()).thenReturn(listRents);

        listRents = rentService.findAll();

        assertEquals(1, listRents.size(), "find all should return one rent");
    }

    @Test
    @DisplayName("Should be create a Rent")
    void shouldCreateARent(){
        when(rentRepository.insert(rent)).thenReturn(rent);

        var response =  rentService.create(rent, 6);

        assertEquals(rent, response);
    }

    @Test
    @DisplayName("Test findById success")
    void shouldReturnAUser_whenFindById(){
        when(rentRepository.findById("5fc7ba0ee7e48d20dc2fbf52")).thenReturn(Optional.of(rent));

        var response = rentService.findById("5fc7ba0ee7e48d20dc2fbf52");
        var stubExpect = rent;

        Assertions.assertEquals(stubExpect, response);
    }

    @Test
    @DisplayName("Shold be update a user")
    void shouldUpdateUser(){
        when(rentRepository.findById(rent.getId())).thenReturn(Optional.ofNullable(rent));
        when(rentRepository.save(rent)).thenReturn(rent);

        var stubExpect = rentService.udpate(rent.getId(), rent, 6);

        assertEquals(stubExpect, rent);
    }

    @Test
    @DisplayName("Shold be delete a rent")
    void shouldDeleteUser(){
        when(rentRepository.findById("5fc7ba0ee7e48d20dc2fbf52")).thenReturn(Optional.ofNullable(rent));
        rentService.delete(user.getId());
        verify(rentRepository, times(1)).deleteById(rent.getId());
    }



}
