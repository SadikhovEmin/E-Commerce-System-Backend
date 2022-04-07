package com.example.ECommerceSystemBackend.Service;

import com.example.ECommerceSystemBackend.Model.Product;
import com.example.ECommerceSystemBackend.Repository.ProductRepository;
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
