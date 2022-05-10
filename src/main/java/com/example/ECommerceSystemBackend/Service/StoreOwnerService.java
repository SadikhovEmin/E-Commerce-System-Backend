package com.example.ECommerceSystemBackend.Service;

import com.example.ECommerceSystemBackend.Model.StoreOwner;
import com.example.ECommerceSystemBackend.Model.DTO.PasswordDTO;
import com.example.ECommerceSystemBackend.Model.DTO.StoreOwnerInfoDTO;
import com.example.ECommerceSystemBackend.Model.DTO.StoreOwnerStoreDTO;
import com.example.ECommerceSystemBackend.Model.Store;
import com.example.ECommerceSystemBackend.Repository.StoreOwnerRepository;
import com.example.ECommerceSystemBackend.Repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreOwnerService {

    @Autowired
    private StoreOwnerRepository storeOwnerRepository;

    @Autowired
    private StoreRepository storeRepository;

    public StoreOwner addStoreOwner(StoreOwner storeOwner) {
        return storeOwnerRepository.save(storeOwner);
    }

    public StoreOwner getStoreOwner(Integer id) {
        return storeOwnerRepository.getStoreOwnerById(id);
    }

    public void updateStoreOwnerMfa(StoreOwner storeOwner, boolean mfa) {storeOwnerRepository.updateStoreOwnerMfaById(storeOwner.getId(), mfa);}

    public void updateStoreOwnerMfa(String email, boolean mfa) {storeOwnerRepository.updateStoreOwnerMfaByEmail(email, mfa);}

    public StoreOwner getStoreOwnerByEmail(String email) {
        return storeOwnerRepository.getStoreOwnerByEmail(email);
    }

    public String getStoreOwnerOldPassword(int id) {
        return storeOwnerRepository.getStoreOwnerOldPassword(id);
    }

    public void updateStoreOwnerPassword(PasswordDTO passwordDTO) {storeOwnerRepository.updateStoreOwnerPassword(passwordDTO.getId(), passwordDTO.getPassword());}

    public void updateStoreOwnerInfo(StoreOwnerInfoDTO storeOwner) {
        storeRepository.updateStoreInfo(storeOwner.getId(),storeOwner.getStoreName());
        storeOwnerRepository.updateStoreOwnerInfo(storeOwner.getId(),storeOwner.getEmail());
    }

    public void createNewStore(StoreOwnerStoreDTO storeOwnerStoreDTO) {
        Store store = new Store(storeOwnerStoreDTO.getStoreName());
        StoreOwner storeOwner = storeOwnerRepository.getStoreOwnerById(storeOwnerStoreDTO.getStoreOwnerID());
        store.setStoreOwner(storeOwner);
        storeRepository.save(store);
    }
}
