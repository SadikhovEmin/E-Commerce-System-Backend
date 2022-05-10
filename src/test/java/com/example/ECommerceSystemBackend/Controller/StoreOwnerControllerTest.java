package com.example.ECommerceSystemBackend.Controller;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.ECommerceSystemBackend.Model.StoreOwner;
import com.example.ECommerceSystemBackend.Repository.StoreOwnerRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StoreOwnerControllerTest {

    @Autowired
    private StoreOwnerRepository storeOwnerRepository;

    @Test
    public void addStoreOwner() {
        StoreOwner so = new StoreOwner("Emre","Kaplan","emre.kaplan@ozyegin.edu.tr","Parola");
        var fetchedSo = storeOwnerRepository.save(so);
        assertTrue(fetchedSo.getId() > 0);
    }
}