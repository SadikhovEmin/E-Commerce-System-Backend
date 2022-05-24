package com.example.ECommerceSystemBackend.Controller;

import com.example.ECommerceSystemBackend.Model.CustomerOrder;
import com.example.ECommerceSystemBackend.Model.enums.Status;
import com.example.ECommerceSystemBackend.Service.CustomerOrderService;
import org.springframework.web.bind.annotation.*;
import org.web3j.abi.datatypes.Int;

@RestController
@RequestMapping(path = "/customerOrder")
@CrossOrigin
public class CustomerOrderController {

    private final CustomerOrderService orderService;

    public CustomerOrderController(CustomerOrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public CustomerOrder save(@RequestBody CustomerOrder order) {
        return orderService.save(order);
    }

    @PutMapping(path = "/{orderId}/customer/{customerId}/store/{storeId}")
    public CustomerOrder createOrder(
            @PathVariable Integer orderId,
            @PathVariable Integer customerId,
            @PathVariable Integer storeId
    ) {
        return orderService.createOrder(orderId, customerId, storeId);
    }

    @PutMapping(path = "/updateStatus/{orderId}/status/{status}")
    public void updateOrderStatus(
            @PathVariable String status,
            @PathVariable Integer orderId
    ) {
        orderService.updateOrderStatus(status , orderId);
    }
}
