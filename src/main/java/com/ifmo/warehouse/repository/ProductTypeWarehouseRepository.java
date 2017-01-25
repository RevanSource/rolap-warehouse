package com.ifmo.warehouse.repository;

import com.ifmo.warehouse.domain.ProductTypeWarehouse;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the ProductType entity.
 */
public interface ProductTypeWarehouseRepository extends JpaRepository<ProductTypeWarehouse,Long> {

}
