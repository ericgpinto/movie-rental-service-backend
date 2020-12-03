package com.technocorp.ericpinto.rentms.service;

import com.technocorp.ericpinto.rentms.model.FilmResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "filmservice", url = "https://swapi.dev/api")
public interface FilmService {

    @RequestMapping(value = "/films/{id}/", method = RequestMethod.GET)
    FilmResponse getFilmById(@PathVariable(value = "id") Integer id);
}
