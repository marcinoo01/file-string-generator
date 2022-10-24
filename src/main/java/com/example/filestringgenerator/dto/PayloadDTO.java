package com.example.filestringgenerator.dto;

import java.util.Set;

public record PayloadDTO(Integer minSize, Integer maxSize, Set<Character> characters, Integer amountOfStrings) {
}
