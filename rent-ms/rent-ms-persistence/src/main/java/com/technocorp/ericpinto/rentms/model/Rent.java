package com.technocorp.ericpinto.rentms.model;

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
    private String id;
    @DBRef
    private User user;
    private Film film;
    private Date initialDate;
    private Date finalDate;


}
