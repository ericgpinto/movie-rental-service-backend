package com.technocorp.ericpinto.rentms.controller;

import com.technocorp.ericpinto.rentms.controller.model.Film;
import com.technocorp.ericpinto.rentms.controller.model.Rent;
import com.technocorp.ericpinto.rentms.controller.model.User;
import com.technocorp.ericpinto.rentms.controller.service.RentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RentControllerTests {

    @Mock
    RentService rentService;

    @InjectMocks
    RentController rentController;

    User user = User.builder().id("5fc7ba0ee7e48d20dc2fbf52").name("Éric").email("eric@gmail.com").build();

    Film film = Film.builder().title("Revenge of the Sith").episodeId(6).director("George Lucas").build();

    Rent rent = Rent.builder()
            .id("5fc7ba0ee7e48d20dc2fbf52").user(user).film(film).initialDate(LocalDateTime.now())
            .finalDate(LocalDateTime.now().plusDays(2))
            .build();

    @Test
    @DisplayName("Should return a list of rents")
    void whenFindAllshouldReturnAListOfRents() {

        List<Rent> listRents = new ArrayList<>();
        listRents.add(rent);

        when(rentService.findAll()).thenReturn(listRents);
        var stubActual = rentController.findAll();

        assertEquals(listRents, stubActual);
    }

    @Test
    @DisplayName("Should return a rent")
    void whenFindById_shouldReturnTheRentFounded() {

        when(rentService.findById(rent.getId())).thenReturn(rent);
        var stubActual = rentController.findById(rent.getId());

        assertEquals(rent, stubActual);
    }


    @Test
    @DisplayName("Should return the created rent")
    void whenCreate_shouldReturnTheRentCreated() {
        when(rentService.create(rent, film.getEpisodeId())).thenReturn(rent);
        var stubActual = rentController.create(rent, film.getEpisodeId());

        assertEquals(rent, stubActual);
    }

    @Test
    @DisplayName("Should return the updated rent")
    void whenUpdateShouldReturnTheRentUpdated() {

        when(rentService.udpate(rent.getId(), rent, film.getEpisodeId())).thenReturn(rent);

        var stubActual = rentController.update(rent.getId(), rent, film.getEpisodeId());

        assertEquals(rent, stubActual);
    }

    @Test
    @DisplayName("Should delete a rent")
    void shouldDeleteAUser(){
        rentController.delete(rent.getId());
        verify(rentService, times(1)).delete(rent.getId());
    }
}
