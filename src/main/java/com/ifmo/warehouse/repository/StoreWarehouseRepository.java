package com.ifmo.warehouse.repository;

import com.ifmo.warehouse.domain.StoreWarehouse;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the ProductType entity.
 */
public interface StoreWarehouseRepository extends JpaRepository<StoreWarehouse,Long> {

}
