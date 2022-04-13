package com.example.ECommerceSystemBackend.Controller;

import com.example.ECommerceSystemBackend.Model.Customer;
import com.example.ECommerceSystemBackend.Model.Email;
import com.example.ECommerceSystemBackend.Model.SystemEmailAccount;
import com.example.ECommerceSystemBackend.Model.enums.Hosts;
import com.example.ECommerceSystemBackend.Model.enums.Ports;
import com.example.ECommerceSystemBackend.Model.DTO.CustomerInfoDTO;
import com.example.ECommerceSystemBackend.Model.DTO.PasswordDTO;
import com.example.ECommerceSystemBackend.Service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/customer")
@RestController
public class CustomerController {
    private Map<String, String> codes = new HashMap<String, String>();
    
    @Autowired
    CustomerService customerService;

    @GetMapping(path = "/{id}")
    public Customer getCustomer(@PathVariable Integer id) {
        return customerService.getCustomer(id);
    }

    @GetMapping
    public List<Customer> getCustomer() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}/password")
    public String getCustomerOldPassword(@PathVariable Integer id) {
        return customerService.getCustomerPassword(id);
    }

    @PutMapping(path = "/{id}/info")
    public void updateCustomerInfo(@RequestBody CustomerInfoDTO customer) {
        customerService.updateCustomerInfo(customer);
    }

    @PutMapping(path = "/{id}/password")
    public void updateCustomerPassword(@RequestBody PasswordDTO passwordDTO) {
        customerService.updateCustomerPassword(passwordDTO);
    }

    @PostMapping
    public Customer addCustomer(@RequestBody Map<String,String> customerMap) {
        Customer customer = new Customer();
        customer.setName(customerMap.get("name"));
        customer.setSurname(customerMap.get("surname"));
        customer.setEmail(customerMap.get("email"));
        customer.setPassword(customerMap.get("password"));
        String verificationCode = codes.get(customer.getEmail());
        String enteredCode = customerMap.get("code");
        if(enteredCode.equals(verificationCode)){
            codes.remove(customer.getEmail()); 
            return customerService.addCustomer(customer);
        }
        return null;
    }

    @PostMapping("/verification")
    public void setVerificiation(@RequestBody Map<String, String> mailMap){
        String customerEmail = mailMap.get("email");
        var systemEmailAcc = new SystemEmailAccount("testforhw123@gmail.com","testforhw123123");
	    var systemEmail = new Email(systemEmailAcc,Hosts.GMAIL_SMTP,Ports.GMAIL_PORT_SSL);
        String code = systemEmail.SendAccountVerificationMessage(customerEmail);
        codes.put(customerEmail, code);
    }

}
