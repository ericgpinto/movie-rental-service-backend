package com.technocorp.ericpinto.rentms.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "users")

public class User {

    @Id
    @ApiModelProperty(value = "User id")
    private String id;
    @ApiModelProperty(value = "Name of user")
    private String name;
    @ApiModelProperty(value = "Email of user")
    private String email;
}
