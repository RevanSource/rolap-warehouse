package com.ifmo.mapper;

import com.ifmo.rolap.domain.Address;
import com.ifmo.warehouse.domain.AddressWarehouse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    Address convert(AddressWarehouse AddressWarehouse);
    List<Address> convertAll(Iterable<AddressWarehouse> addressWarehouse);

}
