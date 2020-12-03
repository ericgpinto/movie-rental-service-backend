package com.technocorp.ericpinto.rentms;

import com.technocorp.ericpinto.rentms.model.FilmResponse;
import com.technocorp.ericpinto.rentms.service.FilmService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/films")
@AllArgsConstructor
public class FilmController {

    private FilmService filmService;

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)    
    public FilmResponse getFilmById(@PathVariable Integer id){
        return filmService.getFilmById(id);
    }


}
