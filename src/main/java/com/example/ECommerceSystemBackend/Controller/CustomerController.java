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
@CrossOrigin
public class CustomerController {
    private Map<String, String> codes = new HashMap<String, String>();
    
    @Autowired
    CustomerService customerService;

    /*@GetMapping(path = "/{id}")
    public Customer getCustomer(@PathVariable Integer id) {
        return customerService.getCustomerById(id);
    }

     */

    @GetMapping(path = "/profile/{email}")
    public Integer getCustomerID(@PathVariable String email) {
        return customerService.getCustomerID(email);
    }

    @GetMapping
    public List<Customer> getCustomers() {
        return customerService.getAllCustomers();
    }

    /*
    @GetMapping("/{email}")
    public String getCustomerPassword(@PathVariable String email) {
        return customerService.loginCustomer(email);
    }
     */

    @GetMapping("/{email}")
    public Customer getCustomer(@PathVariable String email) {
        return customerService.getCustomerByEmail(email);
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
