package com.cloud.demo.web.quote;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.SampleOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
@RequiredArgsConstructor
public class QuoteController {

    private final QuoteRepository repository;
    private final ReactiveMongoTemplate mongoTemplate;

    @Value("${spring.application.name}")
    String appName;

    @RequestMapping("/quote")
    public String homePage(Model model) {
        // final Mono<Quote> quote = repository.findById("1");

        SampleOperation matchStage = Aggregation.sample(5);
        Aggregation aggregation = Aggregation.newAggregation(matchStage);
        Flux<Quote> randomQuote = mongoTemplate.aggregate(aggregation, "quote", Quote.class);

        model.addAttribute("quote", randomQuote.next());

        return "quote";
    }
}
