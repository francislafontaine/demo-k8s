package com.cloud.demo.quote.controller;

import com.cloud.demo.quote.repository.QuoteRepository;
import com.cloud.demo.quote.model.Quote;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class QuoteRestController {

    private final QuoteRepository quoteRepository;

    @GetMapping("/quotes")
    public Flux<Quote> getAllQuotes() {
        return quoteRepository.findAll();
    }

    @PostMapping("/quotes")
    public Mono<Quote> createQuotes(@RequestBody Quote quote) {
        return quoteRepository.save(quote);
    }

    @GetMapping("/quotes/{id}")
    public Mono<ResponseEntity<Quote>> getQuoteById(@PathVariable(value = "id") String quoteId) {
        return quoteRepository.findById(quoteId)
                .map(savedQuote -> ResponseEntity.ok(savedQuote))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/quotes/{id}")
    public Mono<ResponseEntity<Quote>> updateQuote(@PathVariable(value = "id") String quoteId,
                                                    @RequestBody Quote quote) {
        return quoteRepository.findById(quoteId)
                .flatMap(existingQuote -> {
                    existingQuote.setQuoteLine(quote.getQuoteLine());
                    return quoteRepository.save(existingQuote);
                })
                .map(updatedQuote -> new ResponseEntity<>(updatedQuote, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/quotes/{id}")
    public Mono<ResponseEntity<Void>> deleteQuote(@PathVariable(value = "id") String quoteId) {

        return quoteRepository.findById(quoteId)
                .flatMap(existingQuote ->
                        quoteRepository.delete(existingQuote)
                                .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
                )
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Quotes are Sent to the client as Server Sent Events
    @GetMapping(value = "/stream/quotes", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Quote> streamAllQuotes() {
        return quoteRepository.findAll();
    }
}
