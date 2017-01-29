package com.ifmo.warehouse.repository;

import com.ifmo.warehouse.domain.OrderItemWarehouse;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the ProductType entity.
 */
public interface OrderItemWarehouseRepository extends JpaRepository<OrderItemWarehouse, Long> {
//    @Query("select o from OrderItemWarehouse o " +
//            "left join fetch o.customerOrderWarehouse ocow " +
//            "left join fetch o.productWarehouse opw " +
//            "where o.id = (:id)")
//    OrderItemWarehouse fetchWithDeps(@Param("id") Long id);

}
