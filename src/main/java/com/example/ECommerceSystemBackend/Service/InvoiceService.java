package com.example.ECommerceSystemBackend.Service;

import java.util.List;

import com.example.ECommerceSystemBackend.Model.Invoice;
import com.example.ECommerceSystemBackend.Repository.InvoiceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceService {
    
    @Autowired
    private InvoiceRepository invoiceRepository;

    public Invoice addInvoice(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }

    public List<Invoice> getInvoicesByStoreOwnerId(Integer id) {
        return invoiceRepository.getInvoicesByStoreOwnerId(id);
    }
}
