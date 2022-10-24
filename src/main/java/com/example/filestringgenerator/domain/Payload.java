package com.example.filestringgenerator.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@Document
@Data
public class Payload {
    @Id
    private String id;
    @NotNull
    @Positive(message = "payload.minSize must be greater than 0")
    private Integer minSize;
    @NotNull
    @Positive(message = "payload.maxSize must be greater than 0")
    private Integer maxSize;
    @NotNull
    private List<@NotBlank(message = "payload.characters must be provided") Character> characters;
    @NotNull
    @Positive(message = "payload.amountString must be greater than 0")
    private Integer amountOfStrings;
    private State state;
    private String fileName;
}
