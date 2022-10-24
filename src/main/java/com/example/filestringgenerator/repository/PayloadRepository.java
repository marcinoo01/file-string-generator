package com.example.filestringgenerator.repository;

import com.example.filestringgenerator.domain.Payload;
import com.example.filestringgenerator.domain.State;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface PayloadRepository extends ReactiveMongoRepository<Payload, String> {
    Mono<Integer> countAllByStateIsNot(State state);
}
