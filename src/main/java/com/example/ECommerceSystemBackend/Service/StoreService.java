package com.example.ECommerceSystemBackend.Service;

import com.example.ECommerceSystemBackend.Model.DTO.CustomerOrderStoreProductSizeDTO;
import com.example.ECommerceSystemBackend.Model.DTO.StoreConfirmDTO;
import com.example.ECommerceSystemBackend.Model.DTO.StoreDiscountDTO;
import com.example.ECommerceSystemBackend.Model.Store;
import com.example.ECommerceSystemBackend.Model.enums.ConfirmationType;
import com.example.ECommerceSystemBackend.Repository.ProductRepository;
import com.example.ECommerceSystemBackend.Repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreService {

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private ProductRepository productRepository;

    public Store getStoreByStoreOwnerId(Integer id) {
        return storeRepository.getStoreByStoreOwnerId(id);
    }

    public List<CustomerOrderStoreProductSizeDTO> getOrdersWithStore() {
        return storeRepository.getOrdersWithStore();
    }
    public void createNewStore(Store store){
      storeRepository.save(store);
    }

    public void updateDiscountPercentage(StoreDiscountDTO storeDiscountDTO){
        storeRepository.updateDiscountPercentage(storeDiscountDTO.getStoreId(),storeDiscountDTO.getDiscountPercentage());
    }


    public void suspendStore(Integer id) {
        storeRepository.suspendStore(id);
    }

    public void unsuspendProduct(Integer id) {
        storeRepository.unsuspendStore(id);
    }

    public List<Store> getPendingStoreCreations(){
        return storeRepository.getPendingStores(ConfirmationType.WAITING);
    }

    public void updateStoreConfirmationType(StoreConfirmDTO storeConfirmDTO){
        storeRepository.updateStoreConfirmationType(storeConfirmDTO.getStoreId(),storeConfirmDTO.getStoreConfirmationType());
    }

    public List<Store> getStores() {
        return storeRepository.getStores();
    }
}
