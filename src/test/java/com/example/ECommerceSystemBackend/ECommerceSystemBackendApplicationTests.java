package com.example.ECommerceSystemBackend;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.ECommerceSystemBackend.Model.Customer;
import com.example.ECommerceSystemBackend.Model.PlatformManager;
import com.example.ECommerceSystemBackend.Model.StoreOwner;
import com.example.ECommerceSystemBackend.Repository.CustomerRepository;
import com.example.ECommerceSystemBackend.Repository.PlatformManagerRepository;
import com.example.ECommerceSystemBackend.Repository.StoreOwnerRepository;
import com.example.ECommerceSystemBackend.Service.AuthenticationService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class ECommerceSystemBackendApplicationTests {
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private AuthenticationService tm;
	@Autowired
	private StoreOwnerRepository storeOwnerRepository; 
	@Autowired
	private PlatformManagerRepository platformManagerRepository;
	
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
		assertTrue(tm.verifyCode("820431", c.getSecret()));
	}

	@Test
	public void testMfaUpdateForCustomer(){
		customerRepository.updateCustomerMfaByEmail("testforhw123@gmail.com", true);
		Customer c = customerRepository.getCustomerByEmail("testforhw123@gmail.com");
		assertTrue(c.isMfa() == true);
	}
	
	@Test
	public void addStoreOwner(){
		var storeOwner = new StoreOwner("A","B","C@gmail.com","D");
		storeOwnerRepository.save(storeOwner);
		assertTrue(storeOwner.getId() > 0);
	}

	@Test
	public void testMfaUpdateForStoreOwner(){
		storeOwnerRepository.updateStoreOwnerMfaByEmail("C@gmail.com",true);
		StoreOwner so = storeOwnerRepository.getStoreOwnerByEmail("C@gmail.com");
		assertTrue(so.isMfa() == true);
	}
	
	@Test
	public void addPlatformManager(){
		var pm = new PlatformManager("A","B","C@gmail.com","D");
		platformManagerRepository.save(pm);
		assertTrue(pm.getId() > 0);
	}
	@Test
	public void testMfaUpdateForPlatformManager(){
		platformManagerRepository.updatePlatformManagerMfaByEmail("C@gmail.com",true);
		PlatformManager pm = platformManagerRepository.getPlatformManagerByEmail("C@gmail.com");
		assertTrue(pm.isMfa() == true);
	}
}
