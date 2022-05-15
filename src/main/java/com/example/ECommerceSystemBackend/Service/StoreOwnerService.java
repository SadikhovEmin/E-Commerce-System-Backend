package com.example.ECommerceSystemBackend.Service;

import com.example.ECommerceSystemBackend.Model.StoreOwner;
import com.example.ECommerceSystemBackend.Model.DTO.PasswordDTO;
import com.example.ECommerceSystemBackend.Model.DTO.StoreOwnerInfoDTO;
import com.example.ECommerceSystemBackend.Repository.StoreOwnerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreOwnerService {

    @Autowired
    private StoreOwnerRepository storeOwnerRepository;

    public StoreOwner addStoreOwner(StoreOwner storeOwner) {
        return storeOwnerRepository.save(storeOwner);
    }

    public StoreOwner getStoreOwner(Integer id) {
        return storeOwnerRepository.getStoreOwnerById(id);
    }

    public StoreOwner getStoreOwner(String email) {
        return storeOwnerRepository.getStoreOwnerByEmail(email);
    }

    public String getWalletAddressOfStoreOwner(Integer id) {
        return storeOwnerRepository.getWalletAddressOfStoreOwner(id);
    }

    public void updateStoreOwnerMfa(StoreOwner storeOwner, boolean mfa) {
        storeOwnerRepository.updateStoreOwnerMfaById(storeOwner.getId(), mfa);
    }

    public void updateStoreOwnerMfa(String email, boolean mfa) {
        storeOwnerRepository.updateStoreOwnerMfaByEmail(email, mfa);
    }

    public StoreOwner getStoreOwnerByEmail(String email) {
        return storeOwnerRepository.getStoreOwnerByEmail(email);
    }

    public String getStoreOwnerOldPassword(int id) {
        return storeOwnerRepository.getStoreOwnerOldPasword(id);
    }

    public void updateStoreOwnerPassword(PasswordDTO passwordDTO) {
        storeOwnerRepository.updateStoreOwnerPassword(passwordDTO.getId(), passwordDTO.getPassword());
    }

    public void updateStoreOwnerInfo(StoreOwnerInfoDTO storeOwner) {
        storeOwnerRepository.updateStoreOwnerInfo(storeOwner.getId(), storeOwner.getEmail());
    }
    
}
