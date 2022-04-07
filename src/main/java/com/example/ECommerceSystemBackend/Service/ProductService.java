package com.example.ECommerceSystemBackend.service;

import com.example.ECommerceSystemBackend.model.Product;
import com.example.ECommerceSystemBackend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository repository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.repository = productRepository;
    }

    public void addProduct(Product product) {
        repository.save(product);
    }
}
