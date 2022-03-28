package com.example.ECommerceSystemBackend.model;

import java.util.ArrayList;
import java.util.List;

public class Store {
    private List<Product> products;


    public Store() {
        this.products = new ArrayList<>();
    }

    public void addProduct(Product p) {
        products.add(p);
    }

    public void deleteProduct(Product p) {
        products.remove(p);
    }
}
