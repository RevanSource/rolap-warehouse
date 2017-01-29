package com.ifmo.service;

import com.ifmo.rolap.domain.OrderFact;
import com.ifmo.rolap.repository.OrderFactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ChartService {
    @Autowired
    private OrderFactRepository orderFactRepository;

    public String getDatePriceString() {
        List<OrderFact> orderFacts = orderFactRepository.findAll();
        return orderFacts.stream()
                .map(of -> "['" + formatDate(of.getOrderDate()) + "', " + of.getPrice() + "]")
                .reduce((s, s2) -> s2 + "," + s).orElse("");
    }

    public String getDateQuantityString() {
        List<OrderFact> orderFacts = orderFactRepository.findAll();
        return orderFacts.stream()
                .map(of -> "['" + formatDate(of.getOrderDate()) + "', " + of.getQuantity() + "]")
                .reduce((s, s2) -> s2 + "," + s).orElse("");
    }

    private String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM");
        return sdf.format(date);
    }
}
