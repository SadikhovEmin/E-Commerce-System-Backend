package com.example.ECommerceSystemBackend.Controller;

import com.example.ECommerceSystemBackend.Model.Customer;
import com.example.ECommerceSystemBackend.Model.DTO.CustomerInfoDTO;
import com.example.ECommerceSystemBackend.Model.DTO.PasswordDTO;
import com.example.ECommerceSystemBackend.Model.DTO.StoreOwnerInfoDTO;
import com.example.ECommerceSystemBackend.Model.StoreOwner;
import com.example.ECommerceSystemBackend.Repository.StoreOwnerRepository;
import com.example.ECommerceSystemBackend.Service.StoreOwnerService;
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
@CrossOrigin
public class StoreOwnerController {
    private Map<String, String> codes = new HashMap<String, String>();

    @Autowired
    StoreOwnerService storeOwnerService;

    @Autowired
    AuthenticationService authenticationService;
  
    @Autowired
    SystemEmailAccountService systemEmailAccountService;

    @GetMapping("/{email}")
    public StoreOwner getStoreOwner(@PathVariable String email) {
        return storeOwnerService.getStoreOwnerByEmail(email);
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

   
    @PostMapping
    public StoreOwner addStoreOwner(@RequestBody StoreOwner storeOwner) {
        return storeOwnerService.addStoreOwner(storeOwner);
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
