package com.ifmo.warehouse.repository;

import com.ifmo.warehouse.domain.ProductWarehouse;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the ProductType entity.
 */
public interface ProductWarehouseRepository extends JpaRepository<ProductWarehouse,Long> {

}
