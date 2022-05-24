package com.example.ECommerceSystemBackend.Repository;

import com.example.ECommerceSystemBackend.Model.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Integer> {

}
