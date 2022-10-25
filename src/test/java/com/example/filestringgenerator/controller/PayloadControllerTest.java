package com.example.filestringgenerator.controller;


import com.example.filestringgenerator.dto.PayloadIdleResponseDTO;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureWebClient
@AutoConfigureWireMock(port = 8084)
public class PayloadControllerTest {

    @Autowired
    private WebTestClient webTestClient;


    @BeforeEach
    void setUp() {
        WireMock.reset();
    }

    @Test
    void retrieveMovieById() {
        var payloadId = "63583d60d1c8b00d9ef121a8";
        stubFor(get(urlEqualTo("/api/v1/payloads" + payloadId))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("payload.json")));


        //when
        webTestClient.get()
                .uri("/api/v1/payloads/", payloadId)
                .exchange()
                .expectStatus().isOk()
                .expectBody(PayloadIdleResponseDTO.class);
        //then
    }
}
