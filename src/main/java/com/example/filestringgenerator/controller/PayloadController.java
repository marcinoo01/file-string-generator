package com.example.filestringgenerator.controller;

import com.example.filestringgenerator.dto.PayloadDTO;
import com.example.filestringgenerator.dto.PayloadIdleResponseDTO;
import com.example.filestringgenerator.dto.PayloadPostReferenceDTO;
import com.example.filestringgenerator.service.PayloadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/payloads")
public class PayloadController {

    private final PayloadService payloadService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<PayloadPostReferenceDTO> run(@RequestBody @Valid PayloadDTO payloadDTO) {
        return payloadService.run(payloadDTO);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Integer> countRunning() {
        return payloadService.countRunning();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<PayloadIdleResponseDTO> getPayloadContent(@PathVariable String id) {
        return payloadService.getPayloadContent(id);
    }
}
