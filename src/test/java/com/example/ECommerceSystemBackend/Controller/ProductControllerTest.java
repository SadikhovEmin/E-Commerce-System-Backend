package com.example.ECommerceSystemBackend.Controller;

import com.example.ECommerceSystemBackend.Repository.ProductRepository;
import com.example.ECommerceSystemBackend.Repository.StoreOwnerRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductControllerTest {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private StoreOwnerRepository storeOwnerRepository;

    @Test
    public void addProduct() {
        /*
        StoreOwner storeOwner = new StoreOwner("Hasan","Sözer","hasan@ozu.edu.tr","123");

        Store store = new Store("OzU-Store");
        storeOwner.setStore(store);

        List<Product> products = new ArrayList<>();
        products.add(new Product("Nike Air",123.3, ProductType.SHOES,store));
        products.add(new Product("Adidas Ultra Boost",230.2, ProductType.SHOES,store));
        products.add(new Product("Hoodie",131.4, ProductType.CLOTHES,store));

        store.setProducts(products);
        store.setStoreOwner(storeOwner);
        storeOwnerRepository.save(storeOwner);

        List<Integer> fetchedProducts = productRepository.getProductWithType(ProductType.SHOES);
        assertEquals(2, fetchedProducts.size());

         */
    }
}