package com.example.ECommerceSystemBackend.Service;

import com.example.ECommerceSystemBackend.Model.Customer;
import com.example.ECommerceSystemBackend.Model.StoreOwner;
import com.example.ECommerceSystemBackend.Repository.StoreOwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class StoreOwnerService {

    @Autowired
    public StoreOwnerRepository storeOwnerRepository;

    public StoreOwner addStoreOwner(StoreOwner storeOwner) {
        return storeOwnerRepository.save(storeOwner);
    }


}
