package com.technocorp.ericpinto.rentms.service;

import com.technocorp.ericpinto.rentms.model.Film;
import com.technocorp.ericpinto.rentms.model.FilmResponse;
import com.technocorp.ericpinto.rentms.model.User;
import com.technocorp.ericpinto.rentms.repository.FilmRepostory;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "filmservice", url = "https://swapi.dev/api")
public interface FilmService {

    @RequestMapping(value = "/films/{id}/", method = RequestMethod.GET)
    Film getFilmById(@PathVariable(value = "id") Integer id);

    @RequestMapping(value = "/films/", method = RequestMethod.GET)
    FilmResponse findAll();

}
