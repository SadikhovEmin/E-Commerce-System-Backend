package com.example.ECommerceSystemBackend.Controller;

import com.example.ECommerceSystemBackend.Model.Customer;
import com.example.ECommerceSystemBackend.Model.CustomerOrder;
import com.example.ECommerceSystemBackend.Model.DTO.CustomerOrderDateProductSizeDTO;
import com.example.ECommerceSystemBackend.Model.DTO.CustomerOrderStoreProductSizeDTO;
import com.example.ECommerceSystemBackend.Model.DTO.OrderActivityDTO;
import com.example.ECommerceSystemBackend.Model.Email;
import com.example.ECommerceSystemBackend.Model.Product;
import com.example.ECommerceSystemBackend.Model.Store;
import com.example.ECommerceSystemBackend.Model.SystemEmailAccount;
import com.example.ECommerceSystemBackend.Model.enums.Hosts;
import com.example.ECommerceSystemBackend.Model.enums.OrderStatus;
import com.example.ECommerceSystemBackend.Model.enums.Ports;
import com.example.ECommerceSystemBackend.Service.CustomerOrderService;
import com.example.ECommerceSystemBackend.Service.CustomerService;
import com.example.ECommerceSystemBackend.Service.ProductService;
import com.example.ECommerceSystemBackend.Service.StoreService;
import com.example.ECommerceSystemBackend.Service.SystemEmailAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@RestController
@RequestMapping(path = "/customerOrder")
@CrossOrigin
public class CustomerOrderController {

    @Autowired
    StoreService storeService;
    @Autowired
    CustomerService customerService;
    @Autowired
    ProductService productService;

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
    public CustomerOrder save(@RequestBody Map<String, Object> orderMap) throws ParseException {
        Store store = storeService.getStoreById((Integer)orderMap.get("storeId"));
        Customer customer = customerService.getCustomerById((Integer)orderMap.get("customerId"));
        
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = formatter.parse((String)orderMap.get("date"));
        java.sql.Date dateSql = new java.sql.Date(date.getTime());

        OrderStatus status = OrderStatus.valueOf((String)orderMap.get("status"));


        ArrayList<Integer> productsIds=(ArrayList<Integer>) orderMap.get("productIds");
        ArrayList<Integer> quantities=(ArrayList<Integer>) orderMap.get("quantities");
        List<Product> products = new ArrayList<Product>();
        for(int i = 0; i < productsIds.size(); i++){
            Product product = productService.getProductById(productsIds.get(i));
            for(int j = 0 ; j < quantities.get(i) ; j++){
                products.add(product);
            }
        }
        return orderService.save(
            new CustomerOrder(
                    dateSql,
                    (Double) orderMap.get("price"),
                    status,
                    store,
                    customer,
                    products
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
