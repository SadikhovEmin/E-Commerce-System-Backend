package com.example.ECommerceSystemBackend.repository;

import com.example.ECommerceSystemBackend.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    @Query("select c from Customer c where c.id = ?1")
    Customer getCustomerByID (Integer id);
}
