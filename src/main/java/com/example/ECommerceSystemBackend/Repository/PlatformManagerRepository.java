package com.example.ECommerceSystemBackend.Repository;

import javax.transaction.Transactional;

import com.example.ECommerceSystemBackend.Model.PlatformManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface PlatformManagerRepository extends JpaRepository<PlatformManager, Integer> {

    @Query("select pm from PlatformManager pm where pm.id = ?1")
    PlatformManager getPlatformManagerById(Integer id);

    @Query("select pm from PlatformManager pm where pm.email = ?1")
    PlatformManager getPlatformManagerByEmail(String email);

    @Modifying
    @Query("update PlatformManager set mfa = ?2 where id = ?1 ")
    @Transactional
    void updatePlatformManagerMfaById(Integer id, boolean mfa);

    @Modifying
    @Query("update PlatformManager set mfa = ?2 where email = ?1 ")
    @Transactional
    void updatePlatformManagerMfaByEmail(String email, boolean mfa);

}
