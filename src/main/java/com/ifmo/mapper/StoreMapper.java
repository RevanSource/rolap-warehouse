package com.ifmo.mapper;

import com.ifmo.rolap.domain.Store;
import com.ifmo.warehouse.domain.StoreWarehouse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {AddressMapper.class})
public interface StoreMapper {
    @Mapping(target = "address", source = "addressWarehouse")
    Store convert(StoreWarehouse storeWarehouse);
    List<Store> convertAll(Iterable<StoreWarehouse> storeWarehouse);

}
