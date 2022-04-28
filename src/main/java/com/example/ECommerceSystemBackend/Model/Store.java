package com.example.ECommerceSystemBackend.Model;

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

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "store"
    )
    public List<Product> products = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    private StoreOwner storeOwner;

    public Store() {
    }



    public StoreOwner getStoreOwner() {
        return storeOwner;
    }

    public void setStoreOwner(StoreOwner storeOwner) {
        this.storeOwner = storeOwner;
    }

    public Store(String name) {
        this.name = name;
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
}