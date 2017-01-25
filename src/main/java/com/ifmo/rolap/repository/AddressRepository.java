package com.ifmo.rolap.repository;

import com.ifmo.rolap.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the CustomerOrder entity.
 */
public interface AddressRepository extends JpaRepository<Address,Long> {

}
