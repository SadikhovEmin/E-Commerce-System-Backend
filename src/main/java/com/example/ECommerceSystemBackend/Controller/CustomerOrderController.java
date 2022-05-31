package com.example.ECommerceSystemBackend.Controller;

import com.example.ECommerceSystemBackend.Model.CustomerOrder;
import com.example.ECommerceSystemBackend.Model.DTO.CustomerOrderDateProductSizeDTO;
import com.example.ECommerceSystemBackend.Model.DTO.CustomerOrderStoreProductSizeDTO;
import com.example.ECommerceSystemBackend.Model.DTO.OrderActivityDTO;
import com.example.ECommerceSystemBackend.Model.Email;
import com.example.ECommerceSystemBackend.Model.Product;
import com.example.ECommerceSystemBackend.Model.SystemEmailAccount;
import com.example.ECommerceSystemBackend.Model.enums.Hosts;
import com.example.ECommerceSystemBackend.Model.enums.OrderStatus;
import com.example.ECommerceSystemBackend.Model.enums.Ports;
import com.example.ECommerceSystemBackend.Service.CustomerOrderService;
import com.example.ECommerceSystemBackend.Service.SystemEmailAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/customerOrder")
@CrossOrigin
public class CustomerOrderController {

    private final CustomerOrderService orderService;
    private final SystemEmailAccountService systemEmailAccountService;

    public CustomerOrderController(CustomerOrderService orderService, SystemEmailAccountService systemEmailAccountService) {
        this.orderService = orderService;
        this.systemEmailAccountService = systemEmailAccountService;
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

    @GetMapping(path = "/all")
    public List<OrderActivityDTO> getAllOrders() {
        return orderService.getAllOrders();
    }

    @PutMapping(path = "/updateStatus/{orderId}/status/{status}")
    public void updateOrderStatus(
            @PathVariable OrderStatus status,
            @PathVariable Integer orderId
    ) {
        var systemEmailAcc = systemEmailAccountService.getSystemEmailAccount("testforhw123@gmail.com");
        var systemEmail = new Email(systemEmailAcc, Hosts.GMAIL_SMTP, Ports.GMAIL_PORT_SSL);
        orderService.updateOrderStatus(status , orderId);
        systemEmail.SendStatusOfOrderToCustomer(getOrderById(orderId));
    }

    @GetMapping(path = "/{id}")
    public CustomerOrder getOrderById(@PathVariable Integer id) {
        return orderService.getOrderByID(id);
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
