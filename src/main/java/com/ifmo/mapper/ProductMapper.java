package com.ifmo.mapper;

import com.ifmo.rolap.domain.Product;
import com.ifmo.warehouse.domain.ProductWarehouse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ProductTypeMapper.class})
public interface ProductMapper {
    @Mapping(target = "productType", source = "productTypeWarehouse")
    Product convert(ProductWarehouse productWarehouse);
    List<Product> convertAll(Iterable<ProductWarehouse> productWarehouse);

}
