package com.technocorp.ericpinto.review.builderwithlombok.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;

@Getter
public class Child extends Parent {
    private String childName;
    private Integer childAge;

    @Builder(builderMethodName = "childBuilder")
    public Child(String parentName, int parentAge, String childName, int childAge) {
        super(parentName, parentAge);
        this.childName = childName;
        this.childAge = childAge;
    }

    public static ChildBuilder builder(String childName){
        return childBuilder().childName(childName);
    }
}