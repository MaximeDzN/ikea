package io.codoctet.ikea.repository;

import io.codoctet.ikea.domain.Quote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuoteRepository extends JpaRepository<Quote,Long> {

}
