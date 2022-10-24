package com.example.filestringgenerator.service;

import com.example.filestringgenerator.domain.Payload;
import com.example.filestringgenerator.domain.State;
import com.example.filestringgenerator.domain.StringGeneration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class StringGenerationServiceImpl implements StringGenerationService {

    @Override
    public List<String> generatePossibleStrings(StringGeneration stringGeneration) {
        final Payload payload = stringGeneration.getPayload();
        payload.setState(State.RUNNING);
        for (int i = payload.getMinSize(); i < payload.getMaxSize() + 1; i++) {
            allCombinations("", stringGeneration, i);
        }
        payload.setState(State.IDLE);
        return stringGeneration.getCurrString();

    }

    private void allCombinations(String current, StringGeneration stringGeneration, int level) {
        if (level == 0) {
            stringGeneration.setCurrentNumberGeneratedStrings(stringGeneration.getCurrentNumberGeneratedStrings() + 1);
            stringGeneration.getCurrString().add(current);
        } else {
            for (Character c : stringGeneration.getPayload().getCharacters())
                if (stringGeneration.getPayload().getAmountOfStrings() > stringGeneration.getCurrentNumberGeneratedStrings()) {
                    allCombinations(current + c, stringGeneration, level - 1);
                } else {
                    break;
                }
        }
    }
}
