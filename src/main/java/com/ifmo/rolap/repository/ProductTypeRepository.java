package com.ifmo.rolap.repository;

import com.ifmo.rolap.domain.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the ProductType entity.
 */
public interface ProductTypeRepository extends JpaRepository<ProductType,Long> {

}
