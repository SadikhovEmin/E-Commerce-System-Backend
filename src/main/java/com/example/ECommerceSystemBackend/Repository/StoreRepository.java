package com.example.ECommerceSystemBackend.Repository;

import com.example.ECommerceSystemBackend.Model.Store;
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
    List<Store> getPendingStores(String confirmationType);

    @Modifying
    @Query("update Store set storeConfirmationType = ?2 where id = ?1")
    @Transactional
    void updateStoreConfirmationType(Integer storeId, String storeConfirmationType);
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
}
