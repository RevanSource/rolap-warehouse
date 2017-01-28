package com.ifmo.warehouse.repository;

import com.ifmo.warehouse.domain.CustomerOrderWarehouse;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the ProductType entity.
 */
public interface CustomerOrderWarehouseRepository extends JpaRepository<CustomerOrderWarehouse,Long> {

}
