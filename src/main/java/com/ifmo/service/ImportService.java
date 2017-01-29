package com.ifmo.service;

import com.ifmo.mapper.*;
import com.ifmo.rolap.domain.OrderFact;
import com.ifmo.rolap.repository.*;
import com.ifmo.warehouse.domain.*;
import com.ifmo.warehouse.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
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
        List<OrderFact> orderFactsList = createOrderFactList();
        if (!orderFactsList.isEmpty()) {
            OrderFact origin = orderFactsList.get(orderFactsList.size() - 1);
            List<OrderFact> generatedFacts = generateRandomFacts(origin, 100);
            orderFactsList.addAll(generatedFacts);
            orderFactRepository.save(orderFactsList);
        }
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
    private List<OrderFact> generateRandomFacts(OrderFact originalOrderFact, int number) {
        Long id = originalOrderFact.getId();
        List<OrderFact> result = new ArrayList<>(number);
        int i = 0;
        while (i++ <= number) {
            OrderFact of = new OrderFact();
            of.setId(++id);
            of.setProduct(originalOrderFact.getProduct());
            of.setCurrency(originalOrderFact.getCurrency());
            of.setCustomer(originalOrderFact.getCustomer());
            of.setStore(originalOrderFact.getStore());

            of.setPrice(getRandomPrice(originalOrderFact.getPrice()));
            of.setQuantity(getRandomQuantity(
                    originalOrderFact.getQuantity(),
                    originalOrderFact.getPrice(),
                    of.getPrice()));
            of.setOrderDate(incDate(originalOrderFact.getOrderDate(), i));
            of.setPlannedDeliveryDate(incDate(of.getOrderDate(), getRandomWithinRange(1, 10)));

            result.add(of);
        }

        return result;
    }

    private Date incDate(Date date, int days) {
        LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        localDateTime = localDateTime.plusDays(days);
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    private int getRandomPrice(int originPrice) {
        return Math.abs((int) (Math.random() * originPrice + getRandomWithinRange(0, originPrice)));
    }

    private int getRandomWithinRange(int from, int to) {
        return from + (int)(Math.random() * (to + 1));
    }

    private int getRandomQuantity(int origin, int originPrice, int currentPrice) {
        if (currentPrice > originPrice) {
            return (int) (origin * (1 + Math.random()));
        } else {
            return (int) (origin * (1 - Math.random()));
        }
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
