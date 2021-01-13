package com.technocorp.ericpinto.review.builderwithlombok.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@Getter
@AllArgsConstructor
public class Parent {

    @NonNull
    private String parentName;
    private Integer parentAge;
}
