package com.example.ECommerceSystemBackend.Repository;

import com.example.ECommerceSystemBackend.Model.CustomerOrder;
import com.example.ECommerceSystemBackend.Model.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.nio.file.StandardCopyOption;


@Repository
public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Integer> {

    @Modifying
    @Transactional
    @Query("update CustomerOrder o set o.status =?1 where o.id =?2")
    void updateOrderStatus(String status, Integer orderId);
}
