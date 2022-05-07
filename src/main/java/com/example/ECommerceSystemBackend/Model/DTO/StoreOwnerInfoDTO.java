package com.example.ECommerceSystemBackend.Model.DTO;

import com.example.ECommerceSystemBackend.Model.Store;

public class StoreOwnerInfoDTO {

    public Integer id;
    public String email;
    public String storeName;

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Integer getId() {return id;}

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
