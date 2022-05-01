package com.example.ECommerceSystemBackend.Controller;

import java.util.HashMap;
import java.util.Map;

import com.example.ECommerceSystemBackend.Model.Customer;
import com.example.ECommerceSystemBackend.Model.Email;
import com.example.ECommerceSystemBackend.Model.DTO.CustomerInfoDTO;
import com.example.ECommerceSystemBackend.Model.DTO.PasswordDTO;
import com.example.ECommerceSystemBackend.Model.enums.Hosts;
import com.example.ECommerceSystemBackend.Model.enums.Ports;
import com.example.ECommerceSystemBackend.Service.AuthenticationService;
import com.example.ECommerceSystemBackend.Service.CustomerService;
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

@RequestMapping("/customer")
@RestController
@CrossOrigin
public class CustomerController {
    private Map<String, String> codes = new HashMap<String, String>();
    private Map<String, Integer> mfa_codes = new HashMap<String, Integer>();


    @Autowired
    CustomerService customerService;

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    SystemEmailAccountService systemEmailAccountService;


    @GetMapping(path = "/profile/{email}")
    public Integer getCustomerID(@PathVariable String email) {
        return customerService.getCustomerID(email);
    }
    /*
    @GetMapping
    public List<Customer> getCustomers() {
        return customerService.getAllCustomers();
    }
    */

    /*
    @GetMapping("/{email}")
    public String getCustomerPassword(@PathVariable String email) {
        return customerService.loginCustomer(email);
    }
     */

    @GetMapping("/{email}")
    public Customer getCustomer(@PathVariable String email) {
        System.out.println("Hi");
        Customer c = customerService.getCustomerByEmail(email);
        Integer canLogin = mfa_codes.get(c.getEmail());
        mfa_codes.remove(c.getEmail());
        c.setCanLogin(canLogin);
        return c;
    }

    @GetMapping("/{id}/password")
    public String getCustomerOldPassword(@PathVariable String id) {
        return customerService.getCustomerPassword(Integer.parseInt(id));
    }

    @PutMapping(path = "/info")
    public void updateCustomerInfo(@RequestBody CustomerInfoDTO customer) {
        customerService.updateCustomerInfo(customer);
    }

    @PutMapping(path = "/password")
    public void updateCustomerPassword(@RequestBody PasswordDTO passwordDTO) {
        customerService.updateCustomerPassword(passwordDTO);
    }

    @PostMapping
    public String addCustomer(@RequestBody Map<String,String> customerMap) {
        Customer customer = new Customer();
        customer.setName(customerMap.get("name"));
        customer.setSurname(customerMap.get("surname"));
        customer.setEmail(customerMap.get("email"));
        customer.setPassword(customerMap.get("password"));
        customer.setSecret(authenticationService.generateSecret());
        customer.setMfa(Boolean.parseBoolean(customerMap.get("isMfa")));

        var systemEmailAcc = systemEmailAccountService.getSystemEmailAccount("testforhw123@gmail.com");
	    var systemEmail = new Email(systemEmailAcc,Hosts.GMAIL_SMTP,Ports.GMAIL_PORT_SSL);
    
        String verificationCode = codes.get(customer.getEmail());
        String enteredCode = customerMap.get("code");

        if(enteredCode.equals(verificationCode)){
            customerService.addCustomer(customer);
            codes.remove(customer.getEmail()); 
            if(customer.isMfa()){
                var qrCode = authenticationService.getUriForImage(customer.getSecret(),customer.getEmail());
                systemEmail.SendAuthenticationCode(customer.getEmail(),qrCode);
            }
            return "loginPage.html";
        }
        return "signUp.html";
    }

    @PostMapping("/verification")
    public void setVerificiation(@RequestBody Map<String, String> mailMap){
        String customerEmail = mailMap.get("email");
        var systemEmailAcc = systemEmailAccountService.getSystemEmailAccount("testforhw123@gmail.com");
	    var systemEmail = new Email(systemEmailAcc,Hosts.GMAIL_SMTP,Ports.GMAIL_PORT_SSL);
        
        String code = systemEmail.SendAccountVerificationCode(customerEmail);
        codes.put(customerEmail, code);
    }

    @PostMapping("/mfa")
    public void mfa(@RequestBody Map<String, String> customerMap){
        System.out.println("Hello"+customerMap.get("email")+customerMap.get("mfaCode"));
        String customerEmail = customerMap.get("email");
        Customer c = customerService.getCustomerByEmail(customerEmail);
        if(c.isMfa()){
            if(authenticationService.verifyCode(customerMap.get("mfaCode"), c.getSecret())){
                mfa_codes.put(customerEmail,1);
            }
            else{
                mfa_codes.put(customerEmail,0);
            }
        }
        else{
            mfa_codes.put(customerEmail,1);
        }
        System.out.println(mfa_codes.get(customerEmail));
    }

}
