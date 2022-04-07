package com.example.ECommerceSystemBackend.service;

import com.example.ECommerceSystemBackend.Repository.CustomerRepository;
import com.example.ECommerceSystemBackend.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Customer getCustomer(Integer id) {return repository.getCustomerByID(id);}

    public List<Customer> getAllCustomers() {return repository.findAll();}

    public void updateCustomer(Customer customer) { repository.updateCustomerInfo(customer.getId(), customer.getName(), customer.getEmail(), customer.getPassword());}

}
