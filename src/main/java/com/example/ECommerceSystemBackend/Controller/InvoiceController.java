package com.example.ECommerceSystemBackend.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.ECommerceSystemBackend.Model.Customer;
import com.example.ECommerceSystemBackend.Model.Email;
import com.example.ECommerceSystemBackend.Model.Invoice;
import com.example.ECommerceSystemBackend.Model.StoreOwner;
import com.example.ECommerceSystemBackend.Model.enums.Hosts;
import com.example.ECommerceSystemBackend.Model.enums.Ports;
import com.example.ECommerceSystemBackend.Service.CustomerService;
import com.example.ECommerceSystemBackend.Service.InvoiceService;
import com.example.ECommerceSystemBackend.Service.StoreOwnerService;
import com.example.ECommerceSystemBackend.Service.SystemEmailAccountService;

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
    @Autowired
    CustomerService customerService;
    @Autowired
    StoreOwnerService storeOwnerService;
    @Autowired
    SystemEmailAccountService systemEmailAccountService;
    
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

        Customer customer = customerService.getCustomerById(invoice.getCustomerId());
        StoreOwner owner = storeOwnerService.getStoreOwnerById(invoice.getStoreOwnerId());
        var systemEmailAcc = systemEmailAccountService.getSystemEmailAccount("testforhw123@gmail.com");
        var systemEmail = new Email(systemEmailAcc, Hosts.GMAIL_SMTP, Ports.GMAIL_PORT_SSL);
        systemEmail.sendCheckoutMessageToCustomer(owner, customer);
    }
}
