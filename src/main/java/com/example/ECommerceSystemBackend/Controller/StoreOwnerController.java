package com.example.ECommerceSystemBackend.Controller;

import java.util.HashMap;
import java.util.Map;

import com.example.ECommerceSystemBackend.Model.Email;
import com.example.ECommerceSystemBackend.Model.StoreOwner;
import com.example.ECommerceSystemBackend.Model.enums.Hosts;
import com.example.ECommerceSystemBackend.Model.enums.Ports;
import com.example.ECommerceSystemBackend.Service.AuthenticationService;
import com.example.ECommerceSystemBackend.Service.StoreOwnerService;
import com.example.ECommerceSystemBackend.Service.SystemEmailAccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/storeOwner")
@RestController
public class StoreOwnerController {
    private Map<String, String> codes = new HashMap<String, String>();

    @Autowired
    StoreOwnerService storeOwnerService;

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    SystemEmailAccountService systemEmailAccountService;
    
    @PostMapping
    public String addCustomer(@RequestBody Map<String,String> storeOwnerMap) {
        StoreOwner storeOwner = new StoreOwner();
        storeOwner.setName(storeOwnerMap.get("name"));
        storeOwner.setSurname(storeOwnerMap.get("surname"));
        storeOwner.setEmail(storeOwnerMap.get("email"));
        storeOwner.setPassword(storeOwnerMap.get("password"));
        storeOwner.setSecret(authenticationService.generateSecret());
        storeOwner.setMfa(Boolean.parseBoolean(storeOwnerMap.get("isMfa")));

        var systemEmailAcc = systemEmailAccountService.getSystemEmailAccount("testforhw123@gmail.com");
	    var systemEmail = new Email(systemEmailAcc,Hosts.GMAIL_SMTP,Ports.GMAIL_PORT_SSL);
    
        String verificationCode = codes.get(storeOwner.getEmail());
        String enteredCode = storeOwnerMap.get("code");

        if(enteredCode.equals(verificationCode)){
            storeOwnerService.addStoreOwner(storeOwner);
            codes.remove(storeOwner.getEmail()); 
            if(storeOwner.isMfa()){
                var qrCode = authenticationService.getUriForImage(storeOwner.getSecret(),storeOwner.getEmail());
                systemEmail.SendAuthenticationCode(storeOwner.getEmail(),qrCode);
            }
            return "loginPage.html";
        }
        return "signUp.html";
    }

    @PostMapping("/verification")
    public void setVerificiation(@RequestBody Map<String, String> mailMap){
        String storeOwnerEmail = mailMap.get("email");
        var systemEmailAcc = systemEmailAccountService.getSystemEmailAccount("testforhw123@gmail.com");
	    var systemEmail = new Email(systemEmailAcc,Hosts.GMAIL_SMTP,Ports.GMAIL_PORT_SSL);
        
        String code = systemEmail.SendAccountVerificationCode(storeOwnerEmail);
        codes.put(storeOwnerEmail, code);
    }

    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> storeOwnerMap){
        String storeOwnerEmail = storeOwnerMap.get("email");
        StoreOwner c = storeOwnerService.getStoreOwner(storeOwnerEmail);
        if(c.getPassword().equals(storeOwnerMap.get("password"))){
            if(c.isMfa()){
                if(authenticationService.verifyCode(storeOwnerMap.get("code"), c.getSecret())){
                    return "customerHomepage.html";
                }
                return "loginPage.html";
            }
            return "customerHomepage.html";
        }
        return "loginPage.html";
    }

}
