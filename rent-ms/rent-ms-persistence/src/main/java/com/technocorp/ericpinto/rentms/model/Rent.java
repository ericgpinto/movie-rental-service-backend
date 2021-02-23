package com.technocorp.ericpinto.rentms.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "rents")
public class Rent {

    @Id
    @ApiModelProperty(value = "id of rent")
    private String id;
    @ApiModelProperty(value = "user of rent ")
    @DBRef
    private User user;
    @ApiModelProperty(value = "rented movie")
    private Film film;
    @ApiModelProperty(value = "rent start date")
    private LocalDateTime initialDate;
    @ApiModelProperty(value = "rent end date")
    private LocalDateTime finalDate;

}
