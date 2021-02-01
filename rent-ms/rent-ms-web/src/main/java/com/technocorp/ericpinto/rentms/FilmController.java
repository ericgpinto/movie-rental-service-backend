package com.technocorp.ericpinto.rentms;

import com.technocorp.ericpinto.rentms.model.Film;
import com.technocorp.ericpinto.rentms.model.FilmResponse;
import com.technocorp.ericpinto.rentms.model.User;
import com.technocorp.ericpinto.rentms.service.FilmService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/films")
@AllArgsConstructor
public class FilmController {

    private FilmService filmService;

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Film> getFilmById(@PathVariable Integer id){

        if (id < 0){
            return ResponseEntity.badRequest().build();
        }

        Film film = filmService.getFilmById(id);

        if(film == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(film);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public FilmResponse findAllFilms(){
        return filmService.findAll();
    }

}
