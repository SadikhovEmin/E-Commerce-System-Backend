package com.example.ECommerceSystemBackend.Repository;

import com.example.ECommerceSystemBackend.model.Customer;
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
    @Query("update Customer set name = ?2 , email = ?3 , password = ?4  where id = ?1 ")
    @Transactional
    Customer updateCustomerInfo(Integer id, String name, String email, String password);

}
