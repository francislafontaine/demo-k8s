package com.cloud.demo.web.quote;

import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuoteRepository extends ReactiveCrudRepository<Quote, String> {

}
