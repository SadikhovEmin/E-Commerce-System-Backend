package com.example.ECommerceSystemBackend.Controller;

import com.example.ECommerceSystemBackend.Model.DTO.ProductDTO;
import com.example.ECommerceSystemBackend.Model.Product;
import com.example.ECommerceSystemBackend.Service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "/product")
@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public void addProduct(@RequestBody ProductDTO productDTO) {
        productService.addProduct(productDTO);
    }

    @GetMapping(path = "/all")
    public @ResponseBody List<Product> getProducts() {
        return productService.getProducts();
    }

}
