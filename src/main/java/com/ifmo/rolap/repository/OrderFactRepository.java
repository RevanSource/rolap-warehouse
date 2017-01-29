package com.ifmo.rolap.repository;

import com.ifmo.rolap.domain.OrderFact;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the OrderItem entity.
 */
public interface OrderFactRepository extends JpaRepository<OrderFact, Long> {

}
