package com.cloud.demo.web.quote;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;
import static org.springframework.web.reactive.function.BodyInserters.fromPublisher;
import static org.springframework.web.reactive.function.server.ServerResponse.*;

@Component
@RequiredArgsConstructor
public class QuoteHandler {

    private final QuoteRepository repository;

    public Mono<ServerResponse> getById(ServerRequest request) {
        final Mono<Quote> quote = repository.findById(request.pathVariable("id"));

        return quote
                .flatMap(p -> ok().contentType(MediaType.APPLICATION_JSON).body(fromPublisher(quote, Quote.class)))
                .switchIfEmpty(ok().contentType(MediaType.TEXT_HTML)
                        .header("Content-Type", "text/html; charset=UTF-8")
                        .body(BodyInserters.fromValue("Désolé, aucune pensée du jour hahaHA V2!")))
                .log("Test");

    }

    public Mono<ServerResponse> random(ServerRequest request) {
        final Mono<Quote> quote = repository.findById("1");

        return quote
                .flatMap(p -> ok().contentType(MediaType.APPLICATION_JSON).body(fromPublisher(quote, Quote.class)))
                .switchIfEmpty(ok().contentType(MediaType.APPLICATION_JSON)
                        .header("Content-Type", "text/html; charset=UTF-8")
                        .body(BodyInserters.fromValue("Désolé, aucune pensée du jour hahaHA V2!")))
                .log("Test");

    }

    public Mono<ServerResponse> add(ServerRequest request) {
        final Mono<Quote> quote = repository.findById("1");

        return quote
                .flatMap(p -> ok().contentType(MediaType.APPLICATION_JSON).body(fromPublisher(quote, Quote.class)))
                .switchIfEmpty(ok().contentType(MediaType.APPLICATION_JSON)
                        .header("Content-Type", "text/html; charset=UTF-8")
                        .body(BodyInserters.fromValue("Désolé, aucune pensée du jour hahaHA V2!")))
                .log("Test");

    }
}
