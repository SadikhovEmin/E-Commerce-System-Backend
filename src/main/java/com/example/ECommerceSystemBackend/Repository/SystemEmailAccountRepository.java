package com.example.ECommerceSystemBackend.Repository;

import com.example.ECommerceSystemBackend.Model.SystemEmailAccount;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemEmailAccountRepository extends JpaRepository<SystemEmailAccount, Integer> {

    @Query("select sea from SystemEmailAccount sea where sea.id = ?1")
    SystemEmailAccount getSystemEmailAccountById(Integer id);

    @Query("select sea from SystemEmailAccount sea where sea.email = ?1")
    SystemEmailAccount getSystemEmailAccountByEmail(String email);

}
