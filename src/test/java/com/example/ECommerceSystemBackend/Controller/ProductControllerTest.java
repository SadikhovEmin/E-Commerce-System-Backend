package com.example.ECommerceSystemBackend.Controller;

import com.example.ECommerceSystemBackend.Model.Customer;
import com.example.ECommerceSystemBackend.Model.Product;
import com.example.ECommerceSystemBackend.Model.enums.ProductType;
import com.example.ECommerceSystemBackend.Repository.CustomerRepository;
import com.example.ECommerceSystemBackend.Repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductControllerTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void addProduct() {

        List<Product> products = new ArrayList<>();

        products.add(new Product("Nike Air",123.3, ProductType.SHOES));
        products.add(new Product("Adidas Ultra Boost",230.2, ProductType.SHOES));
        products.add(new Product("Hoodie",131.4, ProductType.CLOTHES));

        for (Product product : products) {
            productRepository.save(product);
        }
        List<Integer> fetchedProducts = productRepository.getProductWithType(ProductType.SHOES);
        assertEquals(2, fetchedProducts.size());
    }
}