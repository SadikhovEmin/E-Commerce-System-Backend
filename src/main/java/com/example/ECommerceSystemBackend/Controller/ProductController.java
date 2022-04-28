package com.example.ECommerceSystemBackend.Controller;

import com.example.ECommerceSystemBackend.Model.Product;
import com.example.ECommerceSystemBackend.Model.enums.ProductType;
import com.example.ECommerceSystemBackend.Repository.ProductRepository;
import com.example.ECommerceSystemBackend.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/products")
@RestController
@CrossOrigin
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping
    public List<Product> getProducts() {
        return productService.getProducts();
    }

    @GetMapping("/{id}")
    public Product getProducts(@PathVariable Integer id) {
        return productService.getProductByID(id);
    }

    @GetMapping("/searchType/{type}")
    public List<Integer> getProductWithTypes(@PathVariable ProductType type) {
        return productService.getProductWithType(type);
    }

    @GetMapping("/searchPrice/{price}")
    public List<Integer> getProductsWithPrice(@PathVariable Double price) {
        return productService.getProductWithPrice(price);
    }

    @GetMapping("/searchLessPrice/{price}")
    public List<Integer> getProductLessThanPrice(@PathVariable Double price) {
        return productService.getProductLessThanPrice(price);
    }

    @GetMapping("/searchGreaterPrice/{price}")
    public List<Integer> getProductGreaterThanPrice(@PathVariable Double price) {
        return productService.getProductGreaterThanPrice(price);
    }

    @GetMapping("/searchName/{name}")
    public List<Integer> getProductWithName(@PathVariable String name) {
        return productService.getProductWithName(name);
    }

}
