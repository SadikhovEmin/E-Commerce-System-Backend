package com.example.ECommerceSystemBackend.Controller;

import com.example.ECommerceSystemBackend.Model.DTO.StoreConfirmDTO;
import com.example.ECommerceSystemBackend.Model.DTO.StoreDiscountDTO;
import com.example.ECommerceSystemBackend.Model.Store;
import com.example.ECommerceSystemBackend.Service.ProductService;
import com.example.ECommerceSystemBackend.Service.StoreService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Store getStoreByProductId(@PathVariable String productId) {return productService.getProductById(Integer.parseInt(productId)).getStore();}

    @GetMapping("/pending")
    public List<Store> getPendingStores() {return storeService.getPendingStoreCreations();}

    @PutMapping("/discount")
    public void updateDiscountPercentage(@RequestBody StoreDiscountDTO storeDiscountDTO){storeService.updateDiscountPercentage(storeDiscountDTO);}

    @PutMapping(path = "/{id}/suspend")
    public void suspendStore(@PathVariable Integer id) {
        storeService.suspendStore(id);
    }

    @PutMapping(path = "/{id}/unsuspend")
    public void unsuspendStore(@PathVariable Integer id) {
        storeService.unsuspendProduct(id);
    }

    @PutMapping("/confirmation")
    public void updateDiscountPercentage(@RequestBody StoreConfirmDTO storeConfirmDTO){storeService.updateStoreConfirmationType(storeConfirmDTO);}

}
