package com.example.ECommerceSystemBackend.Controller;

import java.util.List;

import com.example.ECommerceSystemBackend.Model.Product;
import com.example.ECommerceSystemBackend.Model.DTO.ProductStockDTO;
import com.example.ECommerceSystemBackend.Model.enums.ProductType;
import com.example.ECommerceSystemBackend.Service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Product getProductsByID(@PathVariable Integer id) {
        return productService.getProductByID(id);
    }

    @GetMapping("/store/{id}")
    public List<Product> getProductsWithStoreID(@PathVariable String id) {
        return productService.getProductByStoreID(Integer.parseInt(id));
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
    public List<Product> getProductWithName(@PathVariable String name) {
        return productService.getProductWithName(name);
    }

    @PutMapping("/stock")
    public void updateProductQuantity(@RequestBody ProductStockDTO productStockDTO) {
        productService.updateProductQuantity(productStockDTO.getId(),productStockDTO.getQuantity());
        System.out.println(productStockDTO.getId());
        System.out.println(productStockDTO.getQuantity());
    }

    @PostMapping
    public void addProduct(@RequestBody Product product) {
        productService.addProduct(product);
    }

    @GetMapping("/ascending")
    public List<Product> getAscending() {
        return productService.getAscending();
    }

    @GetMapping("/descending")
    public List<Product> getDescending() {
        return productService.getDescending();

    }

}
