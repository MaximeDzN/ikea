package io.codoctet.ikea.service;


import io.codoctet.ikea.domain.Quote;
import io.codoctet.ikea.repository.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.Set;

@Service
public class QuoteService {
    private QuoteRepository quoteRepository;

    @Autowired
    public QuoteService(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    public Quote create(Quote quote){
        return quoteRepository.save(quote);
    }

    public Quote update(Quote quote){
        return quoteRepository.save(quote);
    }

    public void delete(Long id) {
        quoteRepository.deleteById(id);
    }

    public Quote get(Long id){
        return quoteRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Set<Quote> getAll(){
        return new HashSet<>(quoteRepository.findAll());
    }


}
