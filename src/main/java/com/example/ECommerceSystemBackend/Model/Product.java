package com.example.ECommerceSystemBackend.Model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.example.ECommerceSystemBackend.Model.enums.ProductType;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "PRICE")
    private Double price;
    @Column(name = "QUANTITY")
    private Integer quantity;
    @Column(name = "TYPE")
    private ProductType type;
    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "Shop_ID", referencedColumnName = "id")
    public Store store;

    public Product() {
    }

    public Product(String name, Double price, ProductType type,Store store) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.store = store;
    }



    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }
}