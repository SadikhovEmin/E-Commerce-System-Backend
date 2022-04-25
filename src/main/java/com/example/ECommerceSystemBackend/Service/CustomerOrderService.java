package com.example.ECommerceSystemBackend.Service;

import com.example.ECommerceSystemBackend.Model.CustomerOrder;
import com.example.ECommerceSystemBackend.Repository.CustomerOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerOrderService {
    private final CustomerOrderRepository orderRepository;

    @Autowired
    public CustomerOrderService(CustomerOrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public CustomerOrder addOrder(CustomerOrder customerOrder) {
        return orderRepository.save(customerOrder);
    }

}
