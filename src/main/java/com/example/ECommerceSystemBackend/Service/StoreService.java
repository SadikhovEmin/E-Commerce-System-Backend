package com.example.ECommerceSystemBackend.Service;

import com.example.ECommerceSystemBackend.Model.Store;
import com.example.ECommerceSystemBackend.Repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreService {

    @Autowired
    private StoreRepository storeRepository;

    public Store getStoreByStoreOwnerId(Integer id) {
        return storeRepository.getStoreByStoreOwnerId(id);
    }

    public void createNewStore(Store store){
      storeRepository.save(store);
    }
}
