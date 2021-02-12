package com.technocorp.ericpinto.rentms.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Document(collection = "rents")
public class Rent {

    @Id
    @ApiModelProperty(value = "id of rent")
    private String id;
    @DBRef
    @ApiModelProperty(value = "user of rent ")
    private User user;
    @ApiModelProperty(value = "rented movie")
    private Film film;
    @ApiModelProperty(value = "rent start date")
    private Date initialDate;
    @ApiModelProperty(value = "rent end date")
    private Date finalDate;

}
