package com.example.ECommerceSystemBackend.Model;

import com.example.ECommerceSystemBackend.Model.enums.Status;

import javax.persistence.*;
import java.util.Date;

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
    private Double price;
    @Enumerated(EnumType.STRING)
    private Status status;

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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
