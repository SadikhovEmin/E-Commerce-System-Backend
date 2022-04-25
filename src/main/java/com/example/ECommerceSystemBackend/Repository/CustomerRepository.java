package com.example.ECommerceSystemBackend.Repository;

import com.example.ECommerceSystemBackend.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    @Query("select c from Customer c where c.id = ?1")
    Customer getCustomerByID (Integer id);

    @Modifying
    @Query("update Customer set name = ?2 , surname = ?3, email = ?4  where id = ?1 ")
    @Transactional
    Customer updateCustomerInfo(Integer id, String name, String surname, String email);

    @Modifying
    @Query("update Customer set password = ?2 where id = ?1 ")
    @Transactional
    Customer updateCustomerPassword(Integer id, String password);

    @Query("select c.password from Customer c where c.id = ?1")
    String getCustomerPassword(Integer id);

    @Query("select c.password from Customer c where c.email = ?1")
    String getCustomerPasswordByEmail(String email);

    @Query("select c from Customer c where c.email = ?1")
    Customer getCustomerByEmail(String email);

    @Modifying
    @Query("update Customer set mfa = ?2 where id = ?1 ")
    @Transactional
    void updateCustomerMfaById(Integer id, boolean mfa);

    @Modifying
    @Query("update Customer set mfa = ?2 where email = ?1 ")
    @Transactional
    void updateCustomerMfaByEmail(String email, boolean mfa);
}
