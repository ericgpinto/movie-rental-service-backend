package com.technocorp.ericpinto.rentms.service;

import com.technocorp.ericpinto.rentms.model.Film;
import com.technocorp.ericpinto.rentms.model.FilmResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "filmservice", url = "https://swapi.dev/api")
public interface FilmService {

    @GetMapping(value =  "/films/{id}/")
    Film getFilmById(@PathVariable(value = "id") Integer id);

    @GetMapping(value =  "/films/")
    FilmResponse findAll();
}
