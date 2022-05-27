package com.example.ECommerceSystemBackend.Repository;

import com.example.ECommerceSystemBackend.Model.DTO.CustomerOrderStoreProductSizeDTO;
import com.example.ECommerceSystemBackend.Model.Store;
import com.example.ECommerceSystemBackend.Model.enums.ConfirmationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<Store, Integer> {
    
    @Query("select s from Store s where s.storeOwner.id = ?1")
    Store getStoreByStoreOwnerId(Integer id);

    @Query("select s from Store s where s.storeConfirmationType = ?1")
    List<Store> getPendingStores(ConfirmationType confirmationType);

    @Modifying
    @Query("update Store set storeConfirmationType = ?2 where id = ?1")
    @Transactional
    void updateStoreConfirmationType(Integer storeId, ConfirmationType storeConfirmationType);
    @Modifying
    @Query("update Store set name = ?2  where storeOwner.id = ?1 ")
    @Transactional
    void updateStoreInfo(Integer storeOwnerId, String storeName);

    @Modifying
    @Query("update Store set discountPercentage = ?2 where id = ?1")
    @Transactional
    void updateDiscountPercentage(Integer storeId, Integer discountPercentage);

    @Modifying
    @Transactional
    @Query("update Store s set s.suspended=true where s.id =?1")
    void suspendStore(Integer id);

    @Modifying
    @Transactional
    @Query("update Store s set s.suspended=false where s.id =?1")
    void unsuspendStore(Integer id);

    @Query("select s from Store s")
    List<Store> getStores();

    @Query("select new com.example.ECommerceSystemBackend.Model.DTO.CustomerOrderStoreProductSizeDTO(s.name,s.customerOrders.size) from Store s")
    List<CustomerOrderStoreProductSizeDTO> getOrdersWithStore();

}
