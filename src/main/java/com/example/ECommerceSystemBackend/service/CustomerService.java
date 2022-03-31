package com.example.ECommerceSystemBackend.service;

import com.example.ECommerceSystemBackend.model.Customer;
import com.example.ECommerceSystemBackend.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private final CustomerRepository repository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.repository = customerRepository;
    }

    public void addCustomer(Customer customer) {
        repository.save(customer);
    }
}
