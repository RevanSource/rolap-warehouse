package com.ifmo.service;

import com.ifmo.rolap.domain.ProductType;
import com.ifmo.rolap.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImportService {
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private OrderFactRepository orderFactRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private ProductTypeRepository productTypeRepository;

    public void clearAll() {
        orderFactRepository.deleteAll();
        productTypeRepository.deleteAll();
        addressRepository.deleteAll();
        storeRepository.deleteAll();
        customerRepository.deleteAll();
        productRepository.deleteAll();
    }

    public void importAllData() {
        productTypeRepository.save(getAllProductType());
    }

    public List<ProductType> getAllProductType() {
        return null;
//                productRepository
    }

    public String getProductType() {
        return productRepository.findAll().stream().findFirst().get().toString();
    }


}
