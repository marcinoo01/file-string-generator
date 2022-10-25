package com.example.filestringgenerator.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Set;

public record PayloadIdleResponseDTO(@JsonIgnore String id, Integer minSize, Integer maxSize, Set<Character> characters,
                                     String fileName) {
}
