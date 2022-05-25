package com.example.ECommerceSystemBackend.Model;

import javax.persistence.*;
import java.util.List;

@Entity
public class CustomerOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String date;

    @ManyToOne
    @JoinColumn(name = "Customer_ID", referencedColumnName = "id")
    private Customer customer;  // Assigned Customer -> Foreign key

    @ManyToOne
    @JoinColumn(name = "Store_ID", referencedColumnName = "id")
    private Store store;        // Assigned Store -> Foreign key

    @ManyToMany
    @JoinTable(
            name = "Order_Product",
            joinColumns = @JoinColumn(name = "ORDER_ID"),
            inverseJoinColumns = @JoinColumn(name = "PRODUCT_ID")
    )
    private List<Product> products;

    private Double price;

    private String status;

    public CustomerOrder(String date, Double price, String status, Store store, Customer customer, List<Product> products) {
        this.date = date;
        this.price = price;
        this.status = status;
        setStore(store);
        setCustomer(customer);
        setProducts(products);
    }

    public CustomerOrder(String date, Double price, String status, Store store) {
        this.date = date;
        this.price = price;
        this.status = status;
        this.store = store;
        setStore(store);
    }

    public CustomerOrder() {

    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public Integer getId() {
        return id;
    }

    public void setDate(String date) {
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
