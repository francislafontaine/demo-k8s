package com.cloud.demo.web;


import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

public class HelloWebClient {
    private WebClient client = WebClient.create("http://localhost:9802");

    private Mono<ClientResponse> result = client.get()
            .uri("/hello")
            .accept(MediaType.TEXT_PLAIN)
            .exchange();

    public String getResult() {
        return ">> result = " + result.flatMap(res -> res.bodyToMono(String.class)).block();
    }
}
