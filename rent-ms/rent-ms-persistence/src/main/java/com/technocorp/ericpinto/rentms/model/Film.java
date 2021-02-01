package com.technocorp.ericpinto.rentms.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Film {

    private String title;
    @JsonProperty("episode_id")
    private Integer episodeId;
    private String director;

}
