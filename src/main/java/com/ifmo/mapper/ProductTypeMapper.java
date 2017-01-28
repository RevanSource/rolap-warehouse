package com.ifmo.mapper;

import com.ifmo.rolap.domain.ProductType;
import com.ifmo.warehouse.domain.ProductTypeWarehouse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductTypeMapper {
    ProductType convert(ProductTypeWarehouse productTypeWarehouse);
    List<ProductType> convertAll(Iterable<ProductTypeWarehouse> productTypeWarehouse);

}
