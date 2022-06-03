package com.example.ECommerceSystemBackend.Model;

import com.example.ECommerceSystemBackend.Model.enums.OrderStatus;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
public class CustomerOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Date date;

    private Double price;

    private OrderStatus status;

    @ManyToOne
    @JoinColumn(name = "Customer_ID", referencedColumnName = "id")
    private Customer customer;  // Assigned Customer -> Foreign key

    @ManyToOne
    @JoinColumn(name = "Store_ID", referencedColumnName = "id")
    private Store store;        // Assigned Store -> Foreign key

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "Order_Product",
            joinColumns = @JoinColumn(name = "ORDER_ID"),
            inverseJoinColumns = @JoinColumn(name = "PRODUCT_ID")
    )
    private List<Product> products;


    public CustomerOrder(Date date, Double price, OrderStatus status, Store store, Customer customer, List<Product> products) {
        this.date = date;
        this.price = price;
        this.status = status;
        setStore(store);
        setCustomer(customer);
        setProducts(products);
    }

    public CustomerOrder(Date date, Double price, OrderStatus status, Store store, Customer customer) {
        this.date = date;
        this.price = price;
        this.status = status;
        setStore(store);
        setCustomer(customer);
    }

    public CustomerOrder(Integer id, Date date, Double price, List<Product> products) {
        this.id = id;
        this.date = date;
        this.price = price;
        this.products = products;
    }

    public CustomerOrder(Date date, Double price, OrderStatus status, Store store) {
        this.date = date;
        this.price = price;
        this.status = status;
        this.store = store;
        setStore(store);
    }

    public CustomerOrder(Integer id, Date date, Double price) {
        this.id = id;
        this.date = date;
        this.price = price;
    }

    public CustomerOrder(Integer id, Double price) {
        this.id = id;
        this.price = price;
    }

    public CustomerOrder() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
