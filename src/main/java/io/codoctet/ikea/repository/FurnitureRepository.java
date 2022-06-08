package io.codoctet.ikea.repository;

import io.codoctet.ikea.domain.Furniture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FurnitureRepository extends JpaRepository<Furniture,Long> {

}
