package com.technocorp.ericpinto.rentms.model;

import lombok.Data;

@Data
public class FilmResponse {

    private String title;
    private Integer episode_id;
    private String director;
    private String url;
}
