package com.example.ECommerceSystemBackend.Repository;

import org.springframework.stereotype.Repository;

import java.util.List;

import com.example.ECommerceSystemBackend.Model.Invoice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
    
    @Query("select i from Invoice i where i.storeOwnerId = ?1")
    public List<Invoice> getInvoicesByStoreOwnerId(Integer storeOwnerId);
}
