package com.ifmo.warehouse.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

/**
 * A OrderItem.
 */
@Entity
@Table(name = "order_item")
public class OrderItemWarehouse implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "quantity", nullable = false)
    private Integer quantity;
    
    @Column(name = "other_details")
    private String otherDetails;
    
    @ManyToOne
    @JoinColumn(name = "customer_order_id")
    private CustomerOrderWarehouse customerOrderWarehouse;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductWarehouse productWarehouse;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }
    
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getOtherDetails() {
        return otherDetails;
    }
    
    public void setOtherDetails(String otherDetails) {
        this.otherDetails = otherDetails;
    }

    public CustomerOrderWarehouse getCustomerOrderWarehouse() {
        return customerOrderWarehouse;
    }

    public void setCustomerOrderWarehouse(CustomerOrderWarehouse customerOrderWarehouse) {
        this.customerOrderWarehouse = customerOrderWarehouse;
    }

    public ProductWarehouse getProductWarehouse() {
        return productWarehouse;
    }

    public void setProductWarehouse(ProductWarehouse productWarehouse) {
        this.productWarehouse = productWarehouse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OrderItemWarehouse orderItem = (OrderItemWarehouse) o;
        if(orderItem.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, orderItem.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "OrderItem{" +
            "id=" + id +
            ", quantity='" + quantity + "'" +
            ", otherDetails='" + otherDetails + "'" +
            '}';
    }
}
