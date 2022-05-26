package com.example.ECommerceSystemBackend.Repository;

import com.example.ECommerceSystemBackend.Model.CustomerOrder;
import com.example.ECommerceSystemBackend.Model.DTO.CustomerOrderDateProductSizeDTO;
import com.example.ECommerceSystemBackend.Model.DTO.CustomerOrderStoreProductSizeDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;


@Repository
public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Integer> {

    @Modifying
    @Transactional
    @Query("update CustomerOrder o set o.status =?1 where o.id =?2")
    void updateOrderStatus(String status, Integer orderId);

    @Query("select new com.example.ECommerceSystemBackend.Model.DTO.CustomerOrderDateProductSizeDTO(o.date,o.products.size) from CustomerOrder o")
    List<CustomerOrderDateProductSizeDTO> getOrdersWithDate();

    @Query("select distinct new com.example.ECommerceSystemBackend.Model.DTO.CustomerOrderStoreProductSizeDTO(o.store.name,o.products.size) from CustomerOrder o")
    List<CustomerOrderStoreProductSizeDTO> getOrdersWithStore();
}
