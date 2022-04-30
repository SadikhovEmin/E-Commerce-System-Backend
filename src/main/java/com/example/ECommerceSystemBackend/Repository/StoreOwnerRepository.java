package com.example.ECommerceSystemBackend.Repository;


import com.example.ECommerceSystemBackend.Model.Customer;
import com.example.ECommerceSystemBackend.Model.DTO.PasswordDTO;

import javax.transaction.Transactional;


import com.example.ECommerceSystemBackend.Model.StoreOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;


import javax.transaction.Transactional;

@Repository
public interface StoreOwnerRepository extends JpaRepository<StoreOwner, Integer> {


    @Query("select s from StoreOwner s where s.email = ?1")
    StoreOwner getStoreOwnerByEmail(String email);

    @Query("select s.password from StoreOwner s where s.id = ?1")
    String getStoreOwnerOldPasword(int id);

    @Modifying
    @Query("update StoreOwner set password = ?2 where id = ?1 ")
    @Transactional
    void updateStoreOwnerPassword(Integer id, String password);

    @Modifying
    @Query("update StoreOwner set email = ?2  where id = ?1 ")
    @Transactional
    void updateStoreOwnerInfo(Integer id, String email);

    @Query("select so from StoreOwner so where so.id = ?1")
    StoreOwner getStoreOwnerById (Integer id);

    @Modifying
    @Query("update StoreOwner set mfa = ?2 where id = ?1 ")
    @Transactional
    void updateStoreOwnerMfaById(Integer id, boolean mfa);

    @Modifying
    @Query("update StoreOwner set mfa = ?2 where email = ?1 ")
    @Transactional
    void updateStoreOwnerMfaByEmail(String email, boolean mfa);

}
