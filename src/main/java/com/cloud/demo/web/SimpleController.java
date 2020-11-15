package com.cloud.demo.web;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.spring5.context.webflux.IReactiveDataDriverContextVariable;
import org.thymeleaf.spring5.context.webflux.ReactiveDataDriverContextVariable;
import reactor.core.publisher.Mono;

@Controller
@RequiredArgsConstructor
public class SimpleController {

    private final QuoteRepository repository;

    @Value("${spring.application.name}")
    String appName;

    @RequestMapping("/quote")
    public String homePage(Model model) {
        final Mono<Quote> quote = repository.findById("1");

        //IReactiveDataDriverContextVariable reactiveDataDrivenMode =
        //        new ReactiveDataDriverContextVariable(quote, 1);

        //model.addAttribute("movies", reactiveDataDrivenMode);

        // classic, wait repository loaded all and display it.
        //model.addAttribute("movies", movieRepository.findAll());
        model.addAttribute("appName", quote);

        return "quote";
    }
}
