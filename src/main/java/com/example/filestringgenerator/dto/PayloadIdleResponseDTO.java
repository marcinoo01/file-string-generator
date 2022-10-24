package com.example.filestringgenerator.dto;

import java.util.Set;

public record PayloadIdleResponseDTO(Integer minSize, Integer maxSize, Set<Character> characters, String fileName) {
}
