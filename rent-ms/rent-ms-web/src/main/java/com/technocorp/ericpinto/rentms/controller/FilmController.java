package com.technocorp.ericpinto.rentms.controller;

import com.technocorp.ericpinto.rentms.controller.model.Film;
import com.technocorp.ericpinto.rentms.controller.model.FilmResponse;
import com.technocorp.ericpinto.rentms.controller.service.FilmService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("rentapi/films")
@AllArgsConstructor
@Api(value = "API REST Films")
public class FilmController {

    private final FilmService filmService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}")
    @ApiOperation("Returns a film by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns success"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized")
    })
    public Film getFilmById(@PathVariable Integer id){
        return filmService.getFilmById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    @ApiOperation("Returns a list of films")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns success"),
            @ApiResponse(code = 401, message = "Unauthorized")
    })
    public FilmResponse findAllFilms(){
        return filmService.findAll();
    }

}
