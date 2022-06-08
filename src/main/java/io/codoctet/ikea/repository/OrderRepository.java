package io.codoctet.ikea.repository;

import io.codoctet.ikea.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {

}
