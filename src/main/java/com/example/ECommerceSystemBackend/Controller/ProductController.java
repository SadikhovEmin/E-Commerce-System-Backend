package com.example.ECommerceSystemBackend.Controller;

import com.example.ECommerceSystemBackend.Model.Product;
import com.example.ECommerceSystemBackend.Model.enums.ProductType;
import com.example.ECommerceSystemBackend.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/products")
@RestController
@CrossOrigin
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @GetMapping
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/searchType/{type}")
    public List<Integer> getProductWithTypes(@PathVariable ProductType type) {
        return productRepository.getProductWithType(type);
    }

    @GetMapping("/searchPrice/{price}")
    public List<Integer> getProductsWithPrice(@PathVariable Double price) {
        return productRepository.getProductWithPrice(price);
    }

    @GetMapping("/searchLessPrice/{price}")
    public List<Integer> getProductLessThanPrice(@PathVariable Double price) {
        return productRepository.getProductLessThanPrice(price);
    }

    @GetMapping("/searchGreaterPrice/{price}")
    public List<Integer> getProductGreaterThanPrice(@PathVariable Double price) {
        return productRepository.getProductGreaterThanPrice(price);
    }

    @GetMapping("/searchName/{name}")
    public List<Integer> getProductWithName(@PathVariable String name) {
        return productRepository.getProductWithName(name);
    }

}
