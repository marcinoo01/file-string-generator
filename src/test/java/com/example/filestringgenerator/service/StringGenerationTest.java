package com.example.filestringgenerator.service;

import com.example.filestringgenerator.domain.Payload;
import com.example.filestringgenerator.domain.StringGeneration;
import com.example.filestringgenerator.util.TestListUtil;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StringGenerationTest {

    private final static String ILLEGAL_PAYLOAD_EXCEPTION_MSG = "Provided payload is not valid";

    private final StringGenerationServiceImpl stringGenerationService = new StringGenerationServiceImpl();

    @Test
    void checkIfGeneratesRandomStrings() {
        final Payload acevfPayload = new Payload();
        acevfPayload.setMinSize(1);
        acevfPayload.setMaxSize(7);
        acevfPayload.setAmountOfStrings(1000);
        acevfPayload.setCharacters(List.of('a', 'c', 'e', 'v', 'f'));

        final Payload specialCharactersPayload = new Payload();
        specialCharactersPayload.setMinSize(1);
        specialCharactersPayload.setMaxSize(6);
        specialCharactersPayload.setAmountOfStrings(100);
        specialCharactersPayload.setCharacters(List.of(' ', 'b', '%', '7', 'K', ','));

        final StringGeneration stringGeneration = new StringGeneration(acevfPayload);
        final StringGeneration stringGeneration1 = new StringGeneration(specialCharactersPayload);

        final List<String> strings = stringGenerationService.generatePossibleStrings(stringGeneration);
        final List<String> strings1 = stringGenerationService.generatePossibleStrings(stringGeneration1);
        assertIterableEquals(TestListUtil.ACEVF_CHARACTERS_LIST, strings);
        assertIterableEquals(TestListUtil.SPECIAL_CHARACTERS_LIST, strings1);
    }

    @Test
    void checkIfBreaksWhenPayloadIsNotValid() {
        final Payload invalidMinMaxSize1 = new Payload();
        invalidMinMaxSize1.setMinSize(2);
        invalidMinMaxSize1.setMaxSize(1);
        invalidMinMaxSize1.setAmountOfStrings(1000);
        invalidMinMaxSize1.setCharacters(List.of('a', 'c', 'e', 'v', 'f'));

        final Payload invalidMinMaxSize2 = new Payload();
        invalidMinMaxSize2.setMinSize(1);
        invalidMinMaxSize2.setMaxSize(-1);
        invalidMinMaxSize2.setAmountOfStrings(1000);
        invalidMinMaxSize2.setCharacters(List.of('a', 'c', 'e', 'v', 'f'));

        final Payload invalidMinMaxSize3 = new Payload();
        invalidMinMaxSize3.setMinSize(-11);
        invalidMinMaxSize3.setMaxSize(0);
        invalidMinMaxSize3.setAmountOfStrings(1000);
        invalidMinMaxSize3.setCharacters(List.of('a', 'c', 'e', 'v', 'f'));

        final StringGeneration stringGeneration1 = new StringGeneration(invalidMinMaxSize1);
        final StringGeneration stringGeneration2 = new StringGeneration(invalidMinMaxSize2);
        final StringGeneration stringGeneration3 = new StringGeneration(invalidMinMaxSize3);

        final IllegalArgumentException illegalArgumentException1 = assertThrows(IllegalArgumentException.class,
                () -> stringGenerationService.generatePossibleStrings(stringGeneration1));
        final IllegalArgumentException illegalArgumentException2 = assertThrows(IllegalArgumentException.class,
                () -> stringGenerationService.generatePossibleStrings(stringGeneration2));
        final IllegalArgumentException illegalArgumentException3 = assertThrows(IllegalArgumentException.class,
                () -> stringGenerationService.generatePossibleStrings(stringGeneration3));

        assertEquals(ILLEGAL_PAYLOAD_EXCEPTION_MSG, illegalArgumentException1.getMessage());
        assertEquals(ILLEGAL_PAYLOAD_EXCEPTION_MSG, illegalArgumentException2.getMessage());
        assertEquals(ILLEGAL_PAYLOAD_EXCEPTION_MSG, illegalArgumentException3.getMessage());
    }
}
