package com.example.filestringgenerator.service;

import com.example.filestringgenerator.domain.StringGeneration;

import java.util.List;

public interface StringGenerationService {

    List<String> generatePossibleStrings(StringGeneration stringGeneration);
}
