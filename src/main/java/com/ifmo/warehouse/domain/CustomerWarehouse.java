package com.ifmo.warehouse.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "customer")
public class CustomerWarehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "phone", nullable = false)
    private String phone;

    @NotNull
    @Column(name = "email", nullable = false)
    private String email;

    @NotNull
    @Column(name = "other_details", nullable = false)
    private String otherDetails;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private AddressWarehouse addressWarehouse;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOtherDetails() {
        return otherDetails;
    }

    public void setOtherDetails(String otherDetails) {
        this.otherDetails = otherDetails;
    }

    public AddressWarehouse getAddressWarehouse() {
        return addressWarehouse;
    }

    public void setAddressWarehouse(AddressWarehouse addressWarehouse) {
        this.addressWarehouse = addressWarehouse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CustomerWarehouse customerWarehouse = (CustomerWarehouse) o;
        if(customerWarehouse.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, customerWarehouse.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + "'" +
                ", phone='" + phone + "'" +
                ", email='" + email + "'" +
                ", otherDetails='" + otherDetails + "'" +
                '}';
    }
}
