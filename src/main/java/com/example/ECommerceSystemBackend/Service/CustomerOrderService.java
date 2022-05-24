package com.example.ECommerceSystemBackend.Service;

import com.example.ECommerceSystemBackend.Model.Customer;
import com.example.ECommerceSystemBackend.Model.CustomerOrder;
import com.example.ECommerceSystemBackend.Model.Store;
import com.example.ECommerceSystemBackend.Model.enums.Status;
import com.example.ECommerceSystemBackend.Repository.CustomerOrderRepository;
import com.example.ECommerceSystemBackend.Repository.CustomerRepository;
import com.example.ECommerceSystemBackend.Repository.StoreRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerOrderService {

    private final CustomerOrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final StoreRepository storeRepository;


    public CustomerOrderService(
            CustomerOrderRepository orderRepository,
            CustomerRepository customerRepository,
            StoreRepository storeRepository
    ) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.storeRepository = storeRepository;
    }

    public CustomerOrder createOrder(Integer customerOrderID, Integer customerID, Integer storeID) {
        CustomerOrder customerOrder = orderRepository.findById(customerOrderID).get();
        Customer customer = customerRepository.findById(customerID).get();
        Store store = storeRepository.findById(storeID).get();

//        customer.addOrder(customerOrder);
        customerOrder.setStore(store);
        customerOrder.setCustomer(customer);
        return orderRepository.save(customerOrder);
    }

    public void updateOrderStatus(String status, Integer orderID) {
        orderRepository.updateOrderStatus(status, orderID);
    }

    public CustomerOrder save(CustomerOrder order) {
        return orderRepository.save(order);
    }
}
