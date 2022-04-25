package com.example.ECommerceSystemBackend.Controller;

import com.example.ECommerceSystemBackend.Model.CustomerOrder;
import com.example.ECommerceSystemBackend.Service.CustomerOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/customerOrder")
@RestController
@CrossOrigin
public class CustomerOrderController {

    @Autowired
    CustomerOrderService service;

    @PostMapping
    public CustomerOrder addOrder(@RequestBody CustomerOrder order) {
        return service.addOrder(order);
    }
}
