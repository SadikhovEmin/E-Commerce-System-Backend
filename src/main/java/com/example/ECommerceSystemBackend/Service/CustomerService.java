package com.example.ECommerceSystemBackend.Service;

import com.example.ECommerceSystemBackend.Model.Customer;
import com.example.ECommerceSystemBackend.Model.DTO.CustomerInfoDTO;
import com.example.ECommerceSystemBackend.Model.DTO.PasswordDTO;
import com.example.ECommerceSystemBackend.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository repository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.repository = customerRepository;
    }

    public Customer addCustomer(Customer customer) {
        return repository.save(customer);
    }

    public Customer getCustomerById(Integer id) {return repository.getCustomerByID(id);}

    public Customer getCustomerByEmail(String email) {return repository.getCustomerByEmail(email);}

    public String loginCustomer(String email) {return repository.getCustomerPasswordByEmail(email);}

    public List<Customer> getAllCustomers() {return repository.findAll();}

    public void updateCustomerInfo(CustomerInfoDTO customer) { repository.updateCustomerInfo(customer.getId(), customer.getName(),customer.getSurname(), customer.getEmail());}

    public void updateCustomerPassword(PasswordDTO passwordDTO) {repository.updateCustomerPassword(passwordDTO.getId(),passwordDTO.getPassword());}

    public String getCustomerPassword(Integer id){return repository.getCustomerPassword(id);}

    public Integer getCustomerID(String email) {return repository.getCustomerID(email);}
}
