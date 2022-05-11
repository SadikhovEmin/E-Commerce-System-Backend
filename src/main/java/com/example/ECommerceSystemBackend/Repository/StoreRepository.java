package com.example.ECommerceSystemBackend.Repository;

import com.example.ECommerceSystemBackend.Model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface StoreRepository extends JpaRepository<Store, Integer> {
    
    @Query("select s from Store s where s.storeOwner.id = ?1")
    Store getStoreByStoreOwnerId(Integer id);

    @Modifying
    @Query("update Store set name = ?2  where storeOwner.id = ?1 ")
    @Transactional
    void updateStoreInfo(Integer storeOwnerId, String storeName);

}
