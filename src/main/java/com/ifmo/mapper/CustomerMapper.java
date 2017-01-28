package com.ifmo.mapper;

import com.ifmo.rolap.domain.Customer;
import com.ifmo.warehouse.domain.CustomerWarehouse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {AddressMapper.class})
public interface CustomerMapper {
    @Mapping(target = "address", source = "addressWarehouse")
    Customer convert(CustomerWarehouse customerWarehouse);
    List<Customer> convertAll(Iterable<CustomerWarehouse> customerWarehouse);

}
