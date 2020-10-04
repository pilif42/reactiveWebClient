package com.example.demo.service;

import com.example.demo.dto.CustomerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static java.lang.String.format;

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

    public Flux<CustomerDto> getAll() {
        log.debug("About to call the getAll endpoint...");
        return webClient.get().uri("/customers").retrieve().bodyToFlux(CustomerDto.class);
    }

    public Mono<CustomerDto> getOne(Long id) {
        return webClient.get().uri(format("/customers/%d", id)).retrieve().bodyToMono(CustomerDto.class);
    }

    public Long create(CustomerDto customerDto) {
        Mono<ClientResponse> clientResponseMono = webClient.post().uri("/customers")
                .body(BodyInserters.fromPublisher(Mono.just(customerDto), CustomerDto.class))
                .exchange();
        // We block here as we want to return the ID of the newly created customer.
        ClientResponse clientResponse = clientResponseMono.block();

        // We know we get a header following the pattern Location: /customers/5
        return Long.valueOf(clientResponse.headers().asHttpHeaders().get("Location").get(0).substring(11));
    }
}
