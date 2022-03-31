package com.example.ECommerceSystemBackend;

import com.example.ECommerceSystemBackend.model.Customer;
import com.example.ECommerceSystemBackend.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ECommerceSystemBackendApplicationTests {
	@Autowired
	private CustomerRepository customerRepository;

	@Test
	public void addCustomer() {
		Customer customer = new Customer("Esad", "EsadSimitcioglu", "12345");
		customerRepository.save(customer);

		Customer savedCustomer = customerRepository.getCustomerByID(12);

		System.out.println(customer);
		System.out.println("********");
		System.out.println(savedCustomer);

		assertEquals(customer.getId(),savedCustomer.getId());


	}

}
