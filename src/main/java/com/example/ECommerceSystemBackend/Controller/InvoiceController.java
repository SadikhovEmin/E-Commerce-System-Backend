package com.example.ECommerceSystemBackend.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.ECommerceSystemBackend.Model.Invoice;
import com.example.ECommerceSystemBackend.Service.InvoiceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/invoice")
@RestController
@CrossOrigin
public class InvoiceController {

    @Autowired
    InvoiceService invoiceService;
    
    @GetMapping("storeOwner/{storeOwnerId}")
    public List<Invoice> getInvoicesByStoreOwnerId (@PathVariable Integer storeOwnerId) {
        return invoiceService.getInvoicesByStoreOwnerId(storeOwnerId);
    }

    @PostMapping
    public void saveInvoice(@RequestBody Map<String, Object> invoiceMap) {
        Invoice invoice = new Invoice();
        invoice.setCustomerId((Integer) invoiceMap.get("customerId"));
        invoice.setStoreOwnerId((Integer) invoiceMap.get("storeOwnerId"));
        invoice.setIdsOfProducts((ArrayList<Integer>) invoiceMap.get("productIds"));
        invoice.setQuantitiesOfProducts((ArrayList<Integer>) invoiceMap.get("quantities"));
        invoiceService.addInvoice(invoice);
    }
}
