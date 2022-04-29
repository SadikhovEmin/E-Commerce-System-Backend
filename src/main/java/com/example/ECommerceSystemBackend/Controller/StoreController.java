package com.example.ECommerceSystemBackend.Controller;

import com.example.ECommerceSystemBackend.Model.Store;
import com.example.ECommerceSystemBackend.Service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
