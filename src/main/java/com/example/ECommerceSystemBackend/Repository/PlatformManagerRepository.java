package com.example.ECommerceSystemBackend.Repository;

import com.example.ECommerceSystemBackend.Model.PlatformManager;
import com.example.ECommerceSystemBackend.Model.StoreOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface PlatformManagerRepository extends JpaRepository<PlatformManager, Integer> {
    @Query("select p from PlatformManager p where p.id = ?1")
    PlatformManager getPlatformManagerByID (Integer id);

    @Query("select p.password from PlatformManager p where p.id = ?1")
    String getPlatformManagerPassword(Integer id);

    @Modifying
    @Query("update PlatformManager set name = ?2 , surname = ?3, email = ?4  where id = ?1 ")
    @Transactional
    PlatformManager updatePlatformManagerInfo(Integer id, String name, String surname, String email);

    @Modifying
    @Query("update PlatformManager set password = ?2 where id = ?1 ")
    @Transactional
    PlatformManager updatePlatformManagerPassword(Integer id, String password);


}
