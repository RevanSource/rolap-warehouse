package com.ifmo.warehouse.repository;

import com.ifmo.warehouse.domain.CustomerWarehouse;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the ProductType entity.
 */
public interface CustomerWarehouseRepository extends JpaRepository<CustomerWarehouse,Long> {
}
