package com.example.ECommerceSystemBackend.Controller;

import com.example.ECommerceSystemBackend.Model.Store;
import com.example.ECommerceSystemBackend.Service.ProductService;
import com.example.ECommerceSystemBackend.Service.StoreService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/store")
@RestController
@CrossOrigin
public class StoreController {

    @Autowired
    private StoreService storeService;
    @Autowired
    private ProductService productService;

    @GetMapping("/{storeOwnerID}")
    public Store getStoreByStoreOwnerId(@PathVariable String storeOwnerID) {return storeService.getStoreByStoreOwnerId(Integer.parseInt(storeOwnerID));}

    @GetMapping("/product/{productId}")
    public Store getStorebyProductId(@PathVariable String productId) {return productService.getProductById(Integer.parseInt(productId)).getStore();}

}
