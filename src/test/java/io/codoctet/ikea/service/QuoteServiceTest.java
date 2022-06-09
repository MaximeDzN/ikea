package io.codoctet.ikea.service;

import io.codoctet.ikea.domain.Quote;
import io.codoctet.ikea.repository.QuoteRepository;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class QuoteServiceTest {

    @Mock
    private QuoteRepository quoteRepository;

    @InjectMocks
    private QuoteService quoteService;

    @Test
    @DisplayName("quote_getAll_returnAllFromDB")
    void quote_getAll_returnAllFromDB() {

        List<Quote> quoteList = new ArrayList<>();
        int intendedSize = 10;
        for (int i = 0; i < intendedSize; i++) {
            quoteList.add(Quote.builder().id((long) i).build());
        }
        when(quoteRepository.findAll()).thenReturn(quoteList);
        Set<Quote> quoteSet = quoteService.getAll();

        MatcherAssert.assertThat(quoteSet.size(), Matchers.equalTo(intendedSize));
        verify(quoteRepository).findAll();
    }

    @Test
    @DisplayName("quote_getOne_returnOneFromDB")
    void quote_getOne_returnOneFromDB() {

        Long expectedId = 1L;

        Optional<Quote> expectedQuote = Optional.ofNullable(Quote.builder().id(expectedId).date(new Date(0)).sum(150.0F).build());

        when(quoteRepository.findById(expectedId)).thenReturn(expectedQuote);
        Quote quote = quoteService.get(1L);

        MatcherAssert.assertThat(quote, Matchers.hasToString(expectedQuote.get().toString()));
        verify(quoteRepository).findById(expectedId);
    }

    @Test
    @DisplayName("quote_create_insertInDB")
    void quote_create_insertInDB() {

        Quote inputQuote = Quote.builder().date(new Date(0)).sum(150.0F).build();
        Quote expectedQuote = Quote.builder().id(1L).date(new Date(0)).sum(150.0F).build();

        when(quoteRepository.save(inputQuote)).thenReturn(expectedQuote);
        Quote quote = quoteService.create(inputQuote);

        MatcherAssert.assertThat(quote.getId(), Matchers.equalTo(expectedQuote.getId()));
        verify(quoteRepository).save(inputQuote);
    }

    @Test
    @DisplayName("quote_update_insertInDB")
    void quote_update_insertInDB() {


        Quote expectedQuote = Quote.builder().id(1L).date(new Date(0)).sum(150.0F).build();

        when(quoteRepository.save(expectedQuote)).thenReturn(expectedQuote);
        Quote quote = quoteService.update(expectedQuote);

        MatcherAssert.assertThat(quote.getId(), Matchers.equalTo(expectedQuote.getId()));
        verify(quoteRepository).save(expectedQuote);
    }

    @Test
    @DisplayName("quote_delete_deleteFromDB")
    void quote_delete_deleteFromDB() {

        Long deleteId = 1L;
        doNothing().when(quoteRepository).deleteById(deleteId);
        quoteService.delete(deleteId);
        verify(quoteRepository).deleteById(deleteId);

    }
}