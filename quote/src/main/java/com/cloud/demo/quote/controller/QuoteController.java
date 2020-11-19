package com.cloud.demo.quote.controller;

import com.cloud.demo.quote.repository.QuoteRepository;
import com.cloud.demo.quote.model.Quote;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.SampleOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Flux;

@Controller
@RequiredArgsConstructor
public class QuoteController {

    private final ReactiveMongoTemplate mongoTemplate;

    @RequestMapping("/quote")
    public String homePage(Model model) {
        SampleOperation matchStage = Aggregation.sample(5);
        Aggregation aggregation = Aggregation.newAggregation(matchStage);
        Flux<Quote> randomQuote = mongoTemplate.aggregate(aggregation, "quote", Quote.class);

        model.addAttribute("quote", randomQuote.next()
                .defaultIfEmpty(Quote.builder().quoteLine("Aucune pensée du jour!").author("MongoDB").build()));

        return "quote";
    }
}
