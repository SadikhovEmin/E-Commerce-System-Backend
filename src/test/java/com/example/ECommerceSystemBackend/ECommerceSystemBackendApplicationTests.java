package com.example.ECommerceSystemBackend;

import com.example.ECommerceSystemBackend.model.Customer;
import com.example.ECommerceSystemBackend.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ECommerceSystemBackendApplicationTests {
	@Autowired
	private CustomerRepository customerRepository;

	@Test
	public void addCustomer() {
		var customer = new Customer("Esad", "EsadSimitcioglu", "12345");
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





}
