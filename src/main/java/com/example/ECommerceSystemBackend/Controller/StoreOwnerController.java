package com.example.ECommerceSystemBackend.Controller;

import com.example.ECommerceSystemBackend.Model.StoreOwner;
import com.example.ECommerceSystemBackend.Repository.StoreOwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/storeOwner")
@RestController
@CrossOrigin
public class StoreOwnerController {

    @Autowired
    StoreOwnerRepository storeOwnerRepository;

    @PostMapping
    public StoreOwner addStoreOwner(@RequestBody StoreOwner storeOwner) {
        return storeOwnerRepository.save(storeOwner);
    }

}
