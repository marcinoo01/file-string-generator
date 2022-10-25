package com.example.filestringgenerator.service;

import com.example.filestringgenerator.domain.Payload;
import com.example.filestringgenerator.domain.State;
import com.example.filestringgenerator.mapper.PayloadMapper;
import com.example.filestringgenerator.mapper.PayloadMapperImpl;
import com.example.filestringgenerator.repository.PayloadRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import reactor.test.StepVerifier;

import java.util.List;

@DataMongoTest
class PayloadServiceImplTest {

    @Autowired
    private PayloadRepository payloadRepository;
    private final PayloadMapper payloadMapper = new PayloadMapperImpl();
    private final StringGenerationService stringGenerationService = new StringGenerationServiceImpl();

    private final PayloadServiceImpl payloadService = new PayloadServiceImpl(payloadRepository, payloadMapper, stringGenerationService);

    @BeforeEach
    void setUp() {
        final Payload payload1 = new Payload();
        payload1.setCharacters(List.of('a', 'd', 'e'));
        payload1.setId("63581a0c4ebb780b0fd46797");
        payload1.setMinSize(1);
        payload1.setMaxSize(10);
        payload1.setAmountOfStrings(100);
        payload1.setState(State.RUNNING);
        final Payload payload2 = new Payload();
        payload2.setCharacters(List.of('J', 'L', 'e'));
        payload2.setMinSize(1);
        payload2.setMaxSize(10);
        payload2.setAmountOfStrings(100);
        payload2.setState(State.RUNNING);
        final Payload payload3 = new Payload();
        payload3.setCharacters(List.of('a', 'd', 'e', 'g', 'w'));
        payload3.setMinSize(1);
        payload3.setMaxSize(10);
        payload3.setAmountOfStrings(100);
        payload3.setState(State.RUNNING);
        var payloads = List.of(payload2, payload3, payload1);

        payloadRepository
                .saveAll(payloads)
                .blockLast();
    }

    @AfterEach
    void tearDown() {
        payloadRepository
                .deleteAll()
                .block();
    }

    @Test
    void countAllRunning() {
        var payloadMono = payloadRepository.countAllByStateIsNot(State.IDLE);

        StepVerifier
                .create(payloadMono)
                .expectNextCount(3)
                .verifyComplete();
    }

    @Test
    void findById() {
        var payloadMono = payloadRepository.findById("63581a0c4ebb780b0fd46797");
        StepVerifier
                .create(payloadMono)
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    void notFindByIdWhenObjectWithIdNotExist() {
        var payloadMono = payloadRepository.findById("63581a0c4ebb780b0fd462797");
        StepVerifier
                .create(payloadMono)
                .expectNextCount(0)
                .verifyComplete();
    }
}