package com.example.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Service
public class CustomerService {
    private final WebClient webClient;

    public CustomerService() {
        this.webClient = WebClient
                .builder()
                .baseUrl("http://localhost:8080")
                .defaultCookie("cookieKey", "cookieValue")
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    public void getAll() {
        log.debug("About to call the getAll endpoint...");
        // TODO
//        webClient.get().uri("/customers").exchange().
    }
}
