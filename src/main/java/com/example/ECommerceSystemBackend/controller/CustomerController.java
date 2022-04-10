package com.example.ECommerceSystemBackend.Controller;

import com.example.ECommerceSystemBackend.Model.Customer;
import com.example.ECommerceSystemBackend.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/customer")
@RestController
@CrossOrigin
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping(path = "/{email}")
    public String loginCustomer(@PathVariable String email) {
        String pass = customerService.loginCustomer(email);
        System.out.println(email);
        return pass;
    }

    @PutMapping(path = "/{id}")
    public void updateCustomer(@RequestBody Customer customer) {
        customerService.updateCustomer(customer);
    }

    @PostMapping
    public Customer addCustomer(@RequestBody Customer customer) {
        return customerService.addCustomer(customer);
    }


    /*
    f(n) = g(n) + h(n)



     */

}
