package com.example.ECommerceSystemBackend.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "Name")
    public String name;

    @Column
    public String storeConfirmationType;

    @Column(name = "Discount")
    public Integer discountPercentage;

    @Column(name = "SUSPENDED")
    private Boolean suspended;

    @OneToOne(
            cascade = CascadeType.ALL
    )
    public StoreOwner storeOwner = new StoreOwner();

    @JsonIgnore
    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "store"
    )
    public List<Product> products = new ArrayList<>();

    @OneToMany(mappedBy = "store")
    @JsonIgnore
    private List<CustomerOrder> customerOrders = new ArrayList<>();


    public Store() {
    }


    public Store(String name) {
        this.name = name;
    }

    public StoreOwner getStoreOwner() {
        return storeOwner;
    }

    public void setStoreOwner(StoreOwner storeOwner) {
        this.storeOwner = storeOwner;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<CustomerOrder> getCustomerOrders() {
        return customerOrders;
    }

    public void setCustomerOrders(List<CustomerOrder> customerOrders) {
        this.customerOrders = customerOrders;
    }

    public Integer getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(Integer discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public Boolean getSuspended() {
        return suspended;
    }

    public void setSuspended(Boolean suspended) {
        this.suspended = suspended;
    }
}