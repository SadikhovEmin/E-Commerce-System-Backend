package com.example.ECommerceSystemBackend.Repository;

import com.example.ECommerceSystemBackend.Model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<Store, Integer> {
    
    @Query("select s from Store s where s.storeOwner.id = ?1")
    Store getStoreWithSOId(Integer id); // FIXME:  With ?

}
