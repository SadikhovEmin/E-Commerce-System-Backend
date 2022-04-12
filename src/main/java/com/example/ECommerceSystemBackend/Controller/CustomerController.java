package com.example.ECommerceSystemBackend.Controller;

import com.example.ECommerceSystemBackend.Model.Customer;
import com.example.ECommerceSystemBackend.Model.DTO.CustomerInfoDTO;
import com.example.ECommerceSystemBackend.Model.DTO.PasswordDTO;
import com.example.ECommerceSystemBackend.Service.CustomerService;
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

    @GetMapping("/{id}/password")
    public String getCustomerOldPassword(@PathVariable Integer id) {
        return customerService.getCustomerPassword(id);
    }

    @PutMapping(path = "/{id}/info")
    public void updateCustomerInfo(@RequestBody CustomerInfoDTO customer) {
        customerService.updateCustomerInfo(customer);
    }

    @PutMapping(path = "/{id}/password")
    public void updateCustomerPassword(@RequestBody PasswordDTO passwordDTO) {
        customerService.updateCustomerPassword(passwordDTO);
    }

}
