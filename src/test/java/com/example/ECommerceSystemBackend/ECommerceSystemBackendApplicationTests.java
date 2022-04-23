package com.example.ECommerceSystemBackend;

import com.example.ECommerceSystemBackend.Model.Customer;
import com.example.ECommerceSystemBackend.Repository.CustomerRepository;
import com.example.ECommerceSystemBackend.Service.TotpManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class ECommerceSystemBackendApplicationTests {
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired TotpManager tm;
	
	@Test
	public void addCustomer() {
		var customer = new Customer("Esad","Simit" ,"EsadSimitcioglu", "12345");
		customerRepository.save(customer);
		assertTrue(customer.getId() > 0);
	}

	@Test
	public void pullCustomers() {
		var customerList = customerRepository.findAll();

		for (Customer customer : customerList) {
			System.out.println(customer);
		}
		assertFalse(customerList.isEmpty());
	}
	@Test
	public void test2FA(){
		
		Customer c = customerRepository.getCustomerByEmail("testforhw123@gmail.com");
		assertTrue(tm.verifyCode("931893", c.getSecret()));
	}
}
