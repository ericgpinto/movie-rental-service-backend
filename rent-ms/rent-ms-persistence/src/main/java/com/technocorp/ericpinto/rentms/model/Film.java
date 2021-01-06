package com.technocorp.ericpinto.rentms.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Film {

    private String title;
    @JsonProperty("episode_id")
    private Integer episodeId;
    private String director;
}
