package com.cloud.demo.quote.repository;

import com.cloud.demo.quote.model.Quote;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuoteRepository extends ReactiveCrudRepository<Quote, String> {

}
