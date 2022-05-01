package com.example.ECommerceSystemBackend.Controller;

import com.example.ECommerceSystemBackend.Model.Store;
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


    @GetMapping("/{storeOwnerID}")
    public Store getStoreWithSOID(@PathVariable String storeOwnerID){
        return storeService.getStoreWithSOID(Integer.parseInt(storeOwnerID));
    }


}
