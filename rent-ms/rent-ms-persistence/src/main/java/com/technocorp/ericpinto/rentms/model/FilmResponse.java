package com.technocorp.ericpinto.rentms.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class FilmResponse {
    private List<Film> results = new ArrayList<>();

}
