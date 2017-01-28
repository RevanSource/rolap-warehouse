package com.ifmo.warehouse.repository;

import com.ifmo.warehouse.domain.AddressWarehouse;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the ProductType entity.
 */
public interface AddressWarehouseRepository extends JpaRepository<AddressWarehouse,Long> {

}
