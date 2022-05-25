package com.example.ECommerceSystemBackend.Service;

import com.example.ECommerceSystemBackend.Model.Product;
import com.example.ECommerceSystemBackend.Model.enums.ProductType;
import com.example.ECommerceSystemBackend.Model.enums.ConfirmationType;
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

    public void addProduct(Product product) {
        repository.save(product);
    }

    public Product getProductById(Integer id) {
        Product product = repository.getProductById(id);
        product.setPriceWithDiscount();
        return product;
    }

    public List<Product> getProducts() {
        List<Product> productList = repository.findAll();

        for (Product product : productList) {
            product.setPriceWithDiscount();
        }
        return productList;
    }

    public List<Product> getProductByStoreId(Integer id) {
        List<Product> productList = repository.getProductByStoreId(id);

        for (Product product : productList) {
            product.setPriceWithDiscount();
        }
        return productList;
    }

    public List<Product> getProductInAscendingByPrice() {
        List<Product> productList = repository.getProductInAscendingByPrice();

        for (Product product : productList) {
            product.setPriceWithDiscount();
        }
        return productList;
    }

    public List<Product> getProductInDescendingByPrice() {
        List<Product> productList = repository.getProductInDescendingByPrice();

        for (Product product : productList) {
            product.setPriceWithDiscount();
        }
        return productList;
    }

    public List<Integer> getProductWithType(ProductType productType) {
        return repository.getProductWithType(productType);
    }

    public List<Integer> getProductWithPrice(Double price) {
        return repository.getProductWithPrice(price);
    }

    public List<Integer> getProductLessThanPrice(Double price) {
        return repository.getProductLessThanPrice(price);
    }

    public List<Integer> getProductGreaterThanPrice(Double price) {return repository.getProductGreaterThanPrice(price);}

    public List<Product> getProductWithName(String name) {
        return repository.getProductWithName(name);
    }

    public void updateProductQuantity(Integer id, Integer quantity) {
        repository.updateProductQuantity(id, quantity);
    }

    public void updateProduct(Product product) {
        repository.updateProduct(product.getId(), product.getName(), product.getPrice(), product.getQuantity(), product.getType(), product.getDescription());
    }

    public void suspendProduct(Integer id, ConfirmationType confirmationType) {
        repository.suspendProduct(id, confirmationType);
    }

    public List<Product> getProductBySuspended(ConfirmationType confirmationType){
        return repository.getProductsBySuspended(confirmationType);
    }

}
