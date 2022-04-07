package com.example.ECommerceSystemBackend.controller;

import com.example.ECommerceSystemBackend.model.Customer;
import com.example.ECommerceSystemBackend.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/customer")
@RestController
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping(path = "/{id}")
    public Customer getCustomer(@PathVariable Integer id) {
        return customerService.getCustomer(id);
    }

    @GetMapping
    public List<Customer> getCustomer() {
        return customerService.getAllCustomers();
    }

    @PutMapping(path = "/{id}")
    public void updateCustomer(@RequestBody Customer customer) {
        customerService.updateCustomer(customer);
    }

}
