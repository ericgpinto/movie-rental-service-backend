package com.technocorp.ericpinto.rentms.controller;

import com.technocorp.ericpinto.rentms.controller.FilmController;
import com.technocorp.ericpinto.rentms.model.Film;
import com.technocorp.ericpinto.rentms.model.FilmResponse;
import com.technocorp.ericpinto.rentms.service.FilmService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FilmControllerTests {

    @Mock
    FilmService filmService;

    @InjectMocks
    FilmController filmController;

    Film film = Film.builder().title("Revenge of the Sith").episodeId(6).director("George Lucas").build();

    @Test
    @DisplayName("Should return a list of films")
    void whenFindAllshouldReturnAListOfFilms() {

        List<Film> listFilms = new ArrayList<>();
        listFilms.add(film);

        FilmResponse filmResponse = FilmResponse.builder().results(listFilms).build();

        when(filmService.findAll()).thenReturn(filmResponse);
        var stubActual = filmController.findAllFilms();

        Assertions.assertEquals(filmResponse, stubActual);
    }

    @Test
    @DisplayName("Should return a film")
    void whenFindById_shouldReturnTheFilmFounded() {

        when(filmService.getFilmById(film.getEpisodeId())).thenReturn(film);
        var stubActual = filmController.getFilmById(film.getEpisodeId());

        Assertions.assertEquals(film, stubActual);
    }
}
