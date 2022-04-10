package com.example.ECommerceSystemBackend.Service;

import com.example.ECommerceSystemBackend.Model.Customer;
import com.example.ECommerceSystemBackend.Repository.CustomerRepository;
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

    public Customer addCustomer(Customer customer) {
        return repository.save(customer);
    }

    public Customer getCustomer(Integer id) {return repository.getCustomerByID(id);}

    public String loginCustomer(String email) {
        String customerPasswordByEmail = repository.getCustomerPasswordByEmail(email);
        System.out.println("customerPasswordByEmail = " + customerPasswordByEmail);
        return customerPasswordByEmail;
    }

    public List<Customer> getAllCustomers() {return repository.findAll();}

    public void updateCustomer(Customer customer) { repository.updateCustomerInfo(customer.getId(), customer.getName(), customer.getEmail(), customer.getPassword());}

}
