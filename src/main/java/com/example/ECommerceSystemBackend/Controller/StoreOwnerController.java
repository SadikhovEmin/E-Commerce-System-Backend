package com.example.ECommerceSystemBackend.Controller;


import com.example.ECommerceSystemBackend.Model.*;
import com.example.ECommerceSystemBackend.Model.DTO.PasswordDTO;
import com.example.ECommerceSystemBackend.Model.DTO.StoreOwnerInfoDTO;
import com.example.ECommerceSystemBackend.Model.DTO.StoreOwnerStoreDTO;
import com.example.ECommerceSystemBackend.Service.StoreOwnerService;
import java.util.HashMap;
import java.util.Map;
import com.example.ECommerceSystemBackend.Model.StoreOwner;
import com.example.ECommerceSystemBackend.Model.enums.Hosts;
import com.example.ECommerceSystemBackend.Model.enums.Ports;
import com.example.ECommerceSystemBackend.Service.AuthenticationService;
import com.example.ECommerceSystemBackend.Service.StoreService;
import com.example.ECommerceSystemBackend.Service.SystemEmailAccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/storeOwner")
@RestController
@CrossOrigin
public class StoreOwnerController {
    private Map<String, String> codes = new HashMap<String, String>();
    private Map<String, Integer> mfa_codes = new HashMap<String, Integer>();

    @Autowired
    StoreOwnerService storeOwnerService;

    @Autowired
    StoreService storeService;

    @Autowired
    AuthenticationService authenticationService;
    @Autowired
    SystemEmailAccountService systemEmailAccountService;

    @GetMapping("/{email}")
    public StoreOwner getStoreOwner(@PathVariable String email) {
        StoreOwner c = storeOwnerService.getStoreOwnerByEmail(email);
        Integer canLogin = mfa_codes.get(c.getEmail());
        mfa_codes.remove(c.getEmail());
        c.setCanLogin(canLogin);
        return c;
    }

    @GetMapping("/{id}/password")
    public String getStoreOwnerOldPassword(@PathVariable String id) {
        return storeOwnerService.getStoreOwnerOldPassword(Integer.parseInt(id));
    }

    @PutMapping(path = "/password")
    public void updateStoreOwnerPassword(@RequestBody PasswordDTO passwordDTO) {
        storeOwnerService.updateStoreOwnerPassword(passwordDTO);
    }

    @PutMapping(path = "/info")
    public void updateStoreOwnerInfo(@RequestBody StoreOwnerInfoDTO storeOwner) {
        storeOwnerService.updateStoreOwnerInfo(storeOwner);
    }

    @PostMapping("/store")
    public void createNewStore(@RequestBody StoreOwnerStoreDTO storeOwnerStoreDTO){
        storeOwnerService.createNewStore(storeOwnerStoreDTO);
    }
   
    @PostMapping
    public StoreOwner addStoreOwner(@RequestBody StoreOwner storeOwner) {
        return storeOwnerService.addStoreOwner(storeOwner);
    }

    @PostMapping("/verification")
    public void setVerificiation(@RequestBody Map<String, String> mailMap) {
        String storeOwnerEmail = mailMap.get("email");
        var systemEmailAcc = systemEmailAccountService.getSystemEmailAccount("testforhw123@gmail.com");
        var systemEmail = new Email(systemEmailAcc, Hosts.GMAIL_SMTP, Ports.GMAIL_PORT_SSL);

        String code = systemEmail.SendAccountVerificationCode(storeOwnerEmail);
        codes.put(storeOwnerEmail, code);
    }

    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> storeOwnerMap) {
        String storeOwnerEmail = storeOwnerMap.get("email");
        StoreOwner c = storeOwnerService.getStoreOwnerByEmail(storeOwnerEmail);
        if (c.getPassword().equals(storeOwnerMap.get("password"))) {
            if (c.isMfa()) {
                if (authenticationService.verifyCode(storeOwnerMap.get("code"), c.getSecret())) {
                    return "customerHomepage.html";
                }
                return "loginPage.html";
            }
            return "customerHomepage.html";
        }
        return "loginPage.html";
    }

    @PostMapping("/mfa")
    public void mfa(@RequestBody Map<String, String> customerMap) {
        String customerEmail = customerMap.get("email");
        StoreOwner c = storeOwnerService.getStoreOwnerByEmail(customerEmail);
        if (c.isMfa()) {
            if (authenticationService.verifyCode(customerMap.get("mfaCode"), c.getSecret())) {
                mfa_codes.put(customerEmail, 1);
            } else {
                mfa_codes.put(customerEmail, 0);
            }
        } else {
            mfa_codes.put(customerEmail, 1);
        }
    }

}
