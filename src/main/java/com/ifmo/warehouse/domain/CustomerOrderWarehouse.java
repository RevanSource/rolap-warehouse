package com.ifmo.warehouse.domain;

import com.ifmo.warehouse.domain.enumeration.StatusType;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A CustomerOrder.
 */
@Entity
@Table(name = "customer_order")
public class CustomerOrderWarehouse implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "order_date", nullable = false)
    private ZonedDateTime orderDate;

    @NotNull
    @Column(name = "planned_delivery_date", nullable = false)
    private ZonedDateTime plannedDeliveryDate;

    @Column(name = "actual_delivery_date")
    private ZonedDateTime actualDeliveryDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusType status;

    @Column(name = "other_details")
    private String otherDetails;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private StoreWarehouse store;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerWarehouse customer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(ZonedDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public ZonedDateTime getPlannedDeliveryDate() {
        return plannedDeliveryDate;
    }

    public void setPlannedDeliveryDate(ZonedDateTime plannedDeliveryDate) {
        this.plannedDeliveryDate = plannedDeliveryDate;
    }

    public ZonedDateTime getActualDeliveryDate() {
        return actualDeliveryDate;
    }

    public void setActualDeliveryDate(ZonedDateTime actualDeliveryDate) {
        this.actualDeliveryDate = actualDeliveryDate;
    }

    public StatusType getStatus() {
        return status;
    }

    public void setStatus(StatusType status) {
        this.status = status;
    }

    public String getOtherDetails() {
        return otherDetails;
    }

    public void setOtherDetails(String otherDetails) {
        this.otherDetails = otherDetails;
    }

    public StoreWarehouse getStore() {
        return store;
    }

    public void setStore(StoreWarehouse store) {
        this.store = store;
    }

    public CustomerWarehouse getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerWarehouse customer) {
        this.customer = customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CustomerOrderWarehouse customerOrderWarehouse = (CustomerOrderWarehouse) o;
        if(customerOrderWarehouse.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, customerOrderWarehouse.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "CustomerOrder{" +
            "id=" + id +
            ", orderDate='" + orderDate + "'" +
            ", plannedDeliveryDate='" + plannedDeliveryDate + "'" +
            ", actualDeliveryDate='" + actualDeliveryDate + "'" +
            ", status='" + status + "'" +
            ", otherDetails='" + otherDetails + "'" +
            '}';
    }
}
