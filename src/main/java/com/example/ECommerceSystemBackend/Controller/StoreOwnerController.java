package com.example.ECommerceSystemBackend.Controller;

import com.example.ECommerceSystemBackend.Model.Customer;
import com.example.ECommerceSystemBackend.Model.DTO.CustomerInfoDTO;
import com.example.ECommerceSystemBackend.Model.DTO.PasswordDTO;
import com.example.ECommerceSystemBackend.Model.DTO.StoreOwnerInfoDTO;
import com.example.ECommerceSystemBackend.Model.StoreOwner;
import com.example.ECommerceSystemBackend.Repository.StoreOwnerRepository;
import com.example.ECommerceSystemBackend.Service.StoreOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/storeOwner")
@RestController
@CrossOrigin
public class StoreOwnerController {

    @Autowired
    StoreOwnerService storeOwnerService;

    @GetMapping("/{email}")
    public StoreOwner getStoreOwner(@PathVariable String email) {
        return storeOwnerService.getStoreOwnerByEmail(email);
    }

    @GetMapping("/{id}/password")
    public String getStoreOwnerOldPassword(@PathVariable String id) {
        return storeOwnerService.getStoreOwnerOldPassword(Integer.parseInt(id));
    }

    @PutMapping(path = "/password")
    public void updateStoreOwnerPassword(@RequestBody PasswordDTO passwordDTO) {
        storeOwnerService.updateStoreOwnerPassword(passwordDTO);
    }

    @PutMapping(path = "/info")
    public void updateStoreOwnerInfo(@RequestBody StoreOwnerInfoDTO storeOwner) {
        storeOwnerService.updateStoreOwnerInfo(storeOwner);
    }


    @PostMapping
    public StoreOwner addStoreOwner(@RequestBody StoreOwner storeOwner) {
        return storeOwnerService.addStoreOwner(storeOwner);
    }

}
