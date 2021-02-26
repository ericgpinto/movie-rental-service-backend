package com.technocorp.ericpinto.rentms.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Film {

    @ApiModelProperty(value = "title of Film")
    private String title;
    @JsonProperty("episode_id")
    @ApiModelProperty(value = "episode of Film")
    private Integer episodeId;
    @ApiModelProperty(value = "director of Film")
    private String director;

}
