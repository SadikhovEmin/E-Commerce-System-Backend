package com.example.ECommerceSystemBackend.Service;

import com.example.ECommerceSystemBackend.Model.Customer;
import com.example.ECommerceSystemBackend.Model.CustomerOrder;
import com.example.ECommerceSystemBackend.Model.DTO.CustomerOrderDateProductSizeDTO;
import com.example.ECommerceSystemBackend.Model.DTO.CustomerOrderStoreProductSizeDTO;
import com.example.ECommerceSystemBackend.Model.DTO.OrderActivityDTO;
import com.example.ECommerceSystemBackend.Model.Product;
import com.example.ECommerceSystemBackend.Model.Store;
import com.example.ECommerceSystemBackend.Model.enums.OrderStatus;
import com.example.ECommerceSystemBackend.Repository.CustomerOrderRepository;
import com.example.ECommerceSystemBackend.Repository.CustomerRepository;
import com.example.ECommerceSystemBackend.Repository.StoreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerOrderService {

    private final CustomerOrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final StoreRepository storeRepository;


    public CustomerOrderService(
            CustomerOrderRepository orderRepository,
            CustomerRepository customerRepository,
            StoreRepository storeRepository
    ) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.storeRepository = storeRepository;
    }

    public List<CustomerOrderDateProductSizeDTO> getOrdersWithDate(){
        return orderRepository.getOrdersWithDate();
    }


    public CustomerOrder createOrder(Integer customerOrderID, Integer customerID, Integer storeID) {
        CustomerOrder customerOrder = orderRepository.findById(customerOrderID).get();
        Customer customer = customerRepository.findById(customerID).get();
        Store store = storeRepository.findById(storeID).get();

//        customer.addOrder(customerOrder);
        customerOrder.setStore(store);
        customerOrder.setCustomer(customer);
        return orderRepository.save(customerOrder);
    }

    public void updateOrderStatus(OrderStatus status, Integer orderID) {
        orderRepository.updateOrderStatus(status, orderID);
    }

    public CustomerOrder save(CustomerOrder order) {
        return orderRepository.save(order);
    }

    public void cancelCustomerOrder(Integer orderId){orderRepository.deleteById(orderId);}


    public List<OrderActivityDTO> getOrdersByCustomerID(Integer id) {
        return orderRepository.getOrdersByCustomerID(id);
    }

    public List<Product> getProductsUsedInOrder(Integer id) {
        return orderRepository.getProductsUsedInOrder(id);
    }

    public List<OrderActivityDTO> getAllOrders() {
        return orderRepository.getAllOrders();
    }

    public CustomerOrder getOrderByID(Integer id) {
        return orderRepository.getOrderByID(id);
    }
}
