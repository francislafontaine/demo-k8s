package com.cloud.demo.web.quote;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;

@Configuration
public class QuoteRouter {

    @Bean
    public RouterFunction<ServerResponse> route(QuoteHandler quoteHandler) {

        return RouterFunctions
                .route(GET("/api/quote/random"), quoteHandler::random)
                .andRoute(GET("/api/quote/{id}"), quoteHandler::getById)
                .andRoute(POST("/api/quote/add").and(RequestPredicates.accept(MediaType.TEXT_PLAIN)), quoteHandler::add);
    }
}