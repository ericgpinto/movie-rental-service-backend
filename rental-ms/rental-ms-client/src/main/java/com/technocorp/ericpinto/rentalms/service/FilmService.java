package com.technocorp.ericpinto.rentalms.service;

import com.technocorp.ericpinto.rentalms.model.FilmResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "filmService", url = "//swapi.dev/api")
public interface FilmService {

    @RequestMapping("/films/{id}")
    FilmResponse getFilm(@PathVariable("id") String id);

}
