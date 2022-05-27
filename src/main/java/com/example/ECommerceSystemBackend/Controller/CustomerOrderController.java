package com.example.ECommerceSystemBackend.Controller;

import com.example.ECommerceSystemBackend.Model.CustomerOrder;
import com.example.ECommerceSystemBackend.Model.DTO.CustomerOrderDateProductSizeDTO;
import com.example.ECommerceSystemBackend.Model.DTO.CustomerOrderStoreProductSizeDTO;
import com.example.ECommerceSystemBackend.Service.CustomerOrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/customerOrder")
@CrossOrigin
public class CustomerOrderController {

    private final CustomerOrderService orderService;

    public CustomerOrderController(CustomerOrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/date")
    public List<CustomerOrderDateProductSizeDTO> getOrdersWithDate(){
        return orderService.getOrdersWithDate();
    }

    @PostMapping
    public CustomerOrder save(@RequestBody CustomerOrder order) {
        return orderService.save(
            new CustomerOrder(
                    order.getDate(),
                    order.getPrice(),
                    order.getStatus(),
                    order.getStore(),
                    order.getCustomer(),
                    order.getProducts()
            )
        );
    }
}
