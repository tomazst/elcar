package org.elcar.app.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elcar.app.dto.NonceDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Component
public class NonceRequest {
    private final Logger log = LoggerFactory.getLogger(NonceRequest.class);

    private WebClient webClient;

    public NonceRequest() {
        this.webClient = WebClient.builder()
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .baseUrl("https://jsonplaceholder.typicode.com")
                .build();
    }

    public List<NonceDTO> getSomeNonce() {
        Random rand = new Random();
        int number = rand.nextInt(100);

        log.info("Sending request to: https://jsonplaceholder.typicode.com/post/" + number + "/comments");

        WebClient.ResponseSpec responseSpec = this.webClient.get()
                .uri("/post/" + number + "/comments")
                .accept(MediaType.APPLICATION_JSON)
                .acceptCharset(StandardCharsets.UTF_8)
                .retrieve();

        Optional<String> response = responseSpec.bodyToMono(String.class).blockOptional();
        List<NonceDTO> nonceList = new ArrayList<>();
        if (response.isPresent()) {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                nonceList = objectMapper.readValue(response.get(), new TypeReference<List<NonceDTO>>() {
                });
            } catch (JsonProcessingException e) {
                log.error(e.getMessage(), e);
            }
        }
        return nonceList;
    }



}
