package io.codoctet.ikea.repository;

import io.codoctet.ikea.domain.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food,Long> {

}
