package com.technocorp.ericpinto.rentms.controller;

import com.technocorp.ericpinto.rentms.FilmController;
import com.technocorp.ericpinto.rentms.model.Film;
import com.technocorp.ericpinto.rentms.service.FilmService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = FilmController.class)
public class FilmControllerTests {

    List<Film> films = new ArrayList<>();

    @Autowired
    MockMvc mockMvc;

    @MockBean
    FilmService filmService;

    @Test
    public void findAll_shouldReturnAllUsers() throws Exception {

        int episode = 2;

        Film film = Film.builder()
                .title("The Empire Strikes Back")
                .episodeId(episode)
                .director("Irvin Kershner").build();

        films.add(film);

        when(filmService.getFilmById(2)).thenReturn(film);

        mockMvc.perform(get("/films/{id}", episode)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is(film.getTitle())))
                .andExpect(jsonPath("$.director", is(film.getDirector())));
    }

    @Test
    public void shouldReturn404WhenFindUserById() throws Exception {

        int episode = 7;

        Film film = Film.builder()
                .title("The Empire Strikes Back")
                .episodeId(episode)
                .director("Irvin Kershner").build();

        when(filmService.getFilmById(episode)).thenReturn(film);
        this.mockMvc.perform(get("films/{id}", episode)).andExpect(status().isNotFound());
    }
}
