package com.ifmo.rolap.repository;

import com.ifmo.rolap.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the ProductType entity.
 */
public interface StoreRepository extends JpaRepository<Store,Long> {

}
