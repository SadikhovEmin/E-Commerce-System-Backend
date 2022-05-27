package com.example.ECommerceSystemBackend.Controller;

import com.example.ECommerceSystemBackend.Model.CustomerOrder;
import com.example.ECommerceSystemBackend.Model.DTO.CustomerOrderDateProductSizeDTO;
import com.example.ECommerceSystemBackend.Model.DTO.CustomerOrderStoreProductSizeDTO;
import com.example.ECommerceSystemBackend.Model.DTO.OrderActivityDTO;
import com.example.ECommerceSystemBackend.Model.Product;
import com.example.ECommerceSystemBackend.Model.enums.OrderStatus;
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

    @GetMapping("/store")
    public List<CustomerOrderStoreProductSizeDTO> getOrdersWithStore(){
        return orderService.getOrdersWithStore();
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

    @GetMapping(path = "/all")
    public List<OrderActivityDTO> getAllOrders() {
        return orderService.getAllOrders();
    }

    @PutMapping(path = "/updateStatus/{orderId}/status/{status}")
    public void updateOrderStatus(
            @PathVariable OrderStatus status,
            @PathVariable Integer orderId
    ) {
        orderService.updateOrderStatus(status , orderId);
    }

    @GetMapping(path = "customer/{id}")
    public List<OrderActivityDTO> getOrdersByCustomerID(@PathVariable Integer id) {
        return orderService.getOrdersByCustomerID(id);
    }

    @GetMapping(path = "products/customer/{id}")
    public List<Product> getProductsUsedInOrder(@PathVariable Integer id) {
        return orderService.getProductsUsedInOrder(id);
    }

    @DeleteMapping("/{orderId}")
    public void cancelOrder(@PathVariable Integer orderId){
        orderService.cancelCustomerOrder(orderId);
    }
}
