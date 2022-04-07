package com.example.ECommerceSystemBackend.controller;

import com.example.ECommerceSystemBackend.model.Customer;
import com.example.ECommerceSystemBackend.service.CustomerService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/customers")
@CrossOrigin
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public @ResponseBody Customer addCustomer(@RequestBody Customer customer) {
        customerService.addCustomer(customer);
        return customer;
    }
}
