package com.example.filestringgenerator.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class StringGeneration {

    private final Payload payload;
    private final List<String> currString;
    private Integer currentNumberGeneratedStrings;


    public StringGeneration(Payload payload) {
        this.payload = payload;
        currentNumberGeneratedStrings = 0;
        currString = new ArrayList<>();
    }
}
