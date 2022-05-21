package com.example.ECommerceSystemBackend.Service;

import com.example.ECommerceSystemBackend.Model.DTO.StoreDiscountDTO;
import com.example.ECommerceSystemBackend.Model.Store;
import com.example.ECommerceSystemBackend.Repository.ProductRepository;
import com.example.ECommerceSystemBackend.Repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreService {

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private ProductRepository productRepository;

    public Store getStoreByStoreOwnerId(Integer id) {
        return storeRepository.getStoreByStoreOwnerId(id);
    }

    public void createNewStore(Store store){
      storeRepository.save(store);
    }

    public void updateDiscountPercentage(StoreDiscountDTO storeDiscountDTO){
        storeRepository.updateDiscountPercentage(storeDiscountDTO.getStoreId(),storeDiscountDTO.getDiscountPercentage());
    }
}
