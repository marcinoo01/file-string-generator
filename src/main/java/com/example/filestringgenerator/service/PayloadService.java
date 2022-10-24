package com.example.filestringgenerator.service;

import com.example.filestringgenerator.dto.PayloadDTO;
import com.example.filestringgenerator.dto.PayloadIdleResponseDTO;
import com.example.filestringgenerator.dto.PayloadPostReferenceDTO;
import reactor.core.publisher.Mono;

public interface PayloadService {
    Mono<PayloadPostReferenceDTO> run(PayloadDTO payload);

    Mono<Integer> countRunning();

    Mono<PayloadIdleResponseDTO> getPayloadContent(String id);
}
