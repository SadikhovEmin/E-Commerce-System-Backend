package com.example.ECommerceSystemBackend.Repository;

import javax.transaction.Transactional;

import com.example.ECommerceSystemBackend.Model.StoreOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface StoreOwnerRepository extends JpaRepository<StoreOwner, Integer> {

    @Query("select so from StoreOwner so where so.id = ?1")
    StoreOwner getStoreOwnerById (Integer id);

    @Query("select so from StoreOwner so where so.email = ?1")
    StoreOwner getStoreOwnerByEmail(String email);

    @Modifying
    @Query("update StoreOwner set mfa = ?2 where id = ?1 ")
    @Transactional
    void updateStoreOwnerMfaById(Integer id, boolean mfa);

    @Modifying
    @Query("update StoreOwner set mfa = ?2 where email = ?1 ")
    @Transactional
    void updateStoreOwnerMfaByEmail(String email, boolean mfa);
}
