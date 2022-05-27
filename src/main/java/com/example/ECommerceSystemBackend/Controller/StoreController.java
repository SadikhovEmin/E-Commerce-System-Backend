package com.example.ECommerceSystemBackend.Controller;

import com.example.ECommerceSystemBackend.Model.DTO.StoreConfirmDTO;
import com.example.ECommerceSystemBackend.Model.DTO.StoreDiscountDTO;
import com.example.ECommerceSystemBackend.Model.enums.Hosts;
import com.example.ECommerceSystemBackend.Model.enums.Ports;
import com.example.ECommerceSystemBackend.Model.Customer;
import com.example.ECommerceSystemBackend.Model.Email;
import com.example.ECommerceSystemBackend.Model.Store;
import com.example.ECommerceSystemBackend.Model.SystemEmailAccount;
import com.example.ECommerceSystemBackend.Service.CustomerService;
import com.example.ECommerceSystemBackend.Service.ProductService;
import com.example.ECommerceSystemBackend.Service.StoreService;
import com.example.ECommerceSystemBackend.Service.SystemEmailAccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/store")
@RestController
@CrossOrigin
public class StoreController {

    @Autowired
    private StoreService storeService;
    @Autowired
    private ProductService productService;
    @Autowired
    private SystemEmailAccountService systemEmailAccountService;
    @Autowired
    private CustomerService customerService;

    @GetMapping()
    public List<Store> getStores() {return storeService.getStores();}

    @GetMapping("/{storeOwnerID}")
    public Store getStoreByStoreOwnerId(@PathVariable String storeOwnerID) {return storeService.getStoreByStoreOwnerId(Integer.parseInt(storeOwnerID));}

    @GetMapping("/product/{productId}")
    public Store getStoreByProductId(@PathVariable String productId) {return productService.getProductById(Integer.parseInt(productId)).getStore();}

    @GetMapping("/pending")
    public List<Store> getPendingStores() {return storeService.getPendingStoreCreations();}

    @PutMapping("/discount")
    public void updateDiscountPercentage(@RequestBody StoreDiscountDTO storeDiscountDTO){
        storeService.updateDiscountPercentage(storeDiscountDTO);
        Store store = storeService.getStoreById(storeDiscountDTO.getStoreId());
        List<Customer> customers = customerService.getAllCustomers();
        SystemEmailAccount systemEmailAccount = systemEmailAccountService.getSystemEmailAccount("testforhw123@gmail.com");
        Email email = new Email(systemEmailAccount, Hosts.GMAIL_SMTP, Ports.GMAIL_PORT_SSL);
        email.sendCampaignMessageToCustomers(store, storeDiscountDTO.discountPercentage, customers);
    }

    @PutMapping(path = "/{id}/suspend")
    public void suspendStore(@PathVariable Integer id) {
        storeService.suspendStore(id);
        Store store = storeService.getStoreById(id);
        SystemEmailAccount systemEmailAccount = systemEmailAccountService.getSystemEmailAccount("testforhw123@gmail.com");
        Email email = new Email(systemEmailAccount, Hosts.GMAIL_SMTP, Ports.GMAIL_PORT_SSL);
        email.sendStatusOfStoreToOwner(store, store.storeOwner);
    }

    @PutMapping(path = "/{id}/unsuspend")
    public void unsuspendStore(@PathVariable Integer id) {
        storeService.unsuspendProduct(id);
        Store store = storeService.getStoreById(id);
        SystemEmailAccount systemEmailAccount = systemEmailAccountService.getSystemEmailAccount("testforhw123@gmail.com");
        Email email = new Email(systemEmailAccount, Hosts.GMAIL_SMTP, Ports.GMAIL_PORT_SSL);
        email.sendStatusOfStoreToOwner(store, store.storeOwner);
    }

    @PutMapping("/confirmation")
    public void updateDiscountPercentage(@RequestBody StoreConfirmDTO storeConfirmDTO){storeService.updateStoreConfirmationType(storeConfirmDTO);}

}
