package com.example.ECommerceSystemBackend.Service;

import com.example.ECommerceSystemBackend.Model.Customer;
import com.example.ECommerceSystemBackend.Model.DTO.PasswordDTO;
import com.example.ECommerceSystemBackend.Model.DTO.StoreOwnerInfoDTO;
import com.example.ECommerceSystemBackend.Model.StoreOwner;
import com.example.ECommerceSystemBackend.Repository.StoreOwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreOwnerService {

    @Autowired
    public StoreOwnerRepository storeOwnerRepository;

    public StoreOwner addStoreOwner(StoreOwner storeOwner) {
        return storeOwnerRepository.save(storeOwner);
    }


    public StoreOwner getStoreOwnerByEmail(String email) {
        return storeOwnerRepository.getStoreOwnerByEmail(email);
    }

    public String getStoreOwnerOldPassword(int id) {
        return storeOwnerRepository.getStoreOwnerOldPasword(id);
    }

    public void updateStoreOwnerPassword(PasswordDTO passwordDTO) {
        storeOwnerRepository.updateStoreOwnerPassword(passwordDTO.getId(),passwordDTO.getPassword());
    }

    public void updateStoreOwnerInfo(StoreOwnerInfoDTO storeOwner) {
        storeOwnerRepository.updateStoreOwnerInfo(storeOwner.getId(),storeOwner.getEmail());
    }
}
