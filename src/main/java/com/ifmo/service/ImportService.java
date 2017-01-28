package com.ifmo.service;

import com.ifmo.mapper.*;
import com.ifmo.rolap.domain.OrderFact;
import com.ifmo.rolap.repository.*;
import com.ifmo.warehouse.domain.*;
import com.ifmo.warehouse.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

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

    @Autowired
    private ProductTypeWarehouseRepository productTypeWarehouseRepository;
    @Autowired
    private AddressWarehouseRepository addressWarehouseRepository;
    @Autowired
    private CustomerWarehouseRepository customerWarehouseRepository;
    @Autowired
    private CustomerOrderWarehouseRepository customerOrderWarehouseRepository;
    @Autowired
    private StoreWarehouseRepository storeWarehouseRepository;
    @Autowired
    private OrderItemWarehouseRepository orderItemWarehouseRepository;
    @Autowired
    private ProductWarehouseRepository productWarehouseRepository;


    @Autowired
    private ProductTypeMapper productTypeMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private StoreMapper storeMapper;
    @Autowired
    private CustomerMapper customerMapper;

    private void clearAll() {
        orderFactRepository.deleteAll();
        storeRepository.deleteAll();
        customerRepository.deleteAll();
        productRepository.deleteAll();
        addressRepository.deleteAll();
        productTypeRepository.deleteAll();
    }

    public void importAllData() {
        clearAll();
        importData(productTypeWarehouseRepository, productTypeRepository, productTypeMapper::convertAll);
        importData(addressWarehouseRepository, addressRepository, addressMapper::convertAll);
        importData(customerWarehouseRepository, customerRepository, customerMapper::convertAll);
        importData(storeWarehouseRepository, storeRepository, storeMapper::convertAll);
        importData(productWarehouseRepository, productRepository, productMapper::convertAll);

        orderFactRepository.save(createOrderFactList());

    }

    private <A, B>  List<B> importData(CrudRepository<A, Long> source,
                                    CrudRepository<B, Long> dest,
                                    Function<Iterable<A>, List<B>> mappingFunction) {
        Iterable<A> sourceAll = source.findAll();
        List<B> converted = mappingFunction.apply(sourceAll);
        dest.save(converted);
        return converted;
    }

    public String getProductType() {
        return productTypeWarehouseRepository.findAll().stream().findFirst().get().toString();
    }

    public List<OrderFact> createOrderFactList() {
        List<OrderItemWarehouse> orderItemWarehouseList = orderItemWarehouseRepository.findAll();
        List<OrderFact> orderFactList = new ArrayList<>(orderItemWarehouseList.size());
        for (OrderItemWarehouse oiw : orderItemWarehouseList) {
            OrderFact orderFact = createOrderFact(oiw);
            orderFactList.add(orderFact);
        }

        return orderFactList;
    }

    private OrderFact createOrderFact(OrderItemWarehouse orderItemWarehouse) {
        OrderFact orderFact = new OrderFact();
        orderFact.setId(orderItemWarehouse.getId());
        orderFact.setQuantity(orderItemWarehouse.getQuantity());

        CustomerOrderWarehouse customerOrderWarehouse = orderItemWarehouse.getCustomerOrderWarehouse();
        orderFact.setActualDeliveryDate(customerOrderWarehouse.getActualDeliveryDate());
        orderFact.setOrderDate(customerOrderWarehouse.getOrderDate());
        orderFact.setPlannedDeliveryDate(customerOrderWarehouse.getPlannedDeliveryDate());

        ProductWarehouse productWarehouse = orderItemWarehouse.getProductWarehouse();
        orderFact.setPrice(productWarehouse.getPrice());
        orderFact.setCurrency(productWarehouse.getCurrency());
        orderFact.setProduct(productMapper.convert(productWarehouse));

        CustomerWarehouse customerWarehouse = customerOrderWarehouse.getCustomerWarehouse();
        orderFact.setCustomer(customerMapper.convert(customerWarehouse));

        StoreWarehouse storeWarehouse = customerOrderWarehouse.getStoreWarehouse();
        orderFact.setStore(storeMapper.convert(storeWarehouse));
        return orderFact;
    }


}
