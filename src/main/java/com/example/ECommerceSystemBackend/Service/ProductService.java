package com.example.ECommerceSystemBackend.Service;

import com.example.ECommerceSystemBackend.Model.Product;
import com.example.ECommerceSystemBackend.Model.enums.ProductType;
import com.example.ECommerceSystemBackend.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.repository = productRepository;
    }

    public Product addProduct(Product product) {
        return repository.save(product);
    }

    public List<Product> getProducts() {
        return repository.findAll();
    }

    public List<Product> getProductByStoreID(Integer id) {
        return repository.getProductByStoreID(id);
    }

    public Product getProductByID(Integer id) {
        return repository.getProductByID(id);

    public List<Product> getAscending() {
        return repository.getProductInAscendingOrder();
    }

    public List<Product> getDescending() {
        return repository.getProductInDescendingOrder();
    }

    }

    public List<Integer> getProductWithType(ProductType productType){return repository.getProductWithType(productType);}

    public List<Integer> getProductWithPrice(Double price){return repository.getProductWithPrice(price);}

    public List<Integer> getProductLessThanPrice(Double price){return repository.getProductLessThanPrice(price);}

    public List<Integer> getProductGreaterThanPrice(Double price){return repository.getProductGreaterThanPrice(price);}

    public List<Product> getProductWithName(String name){return repository.getProductWithName(name);}

    public void updateProductQuantity(Integer id,Integer quantity){repository.updateProductQuantity(id,quantity);}

}
