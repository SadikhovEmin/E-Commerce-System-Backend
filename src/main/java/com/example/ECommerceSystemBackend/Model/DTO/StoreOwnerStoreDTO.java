package com.example.ECommerceSystemBackend.Model.DTO;

public class StoreOwnerStoreDTO {
    public Integer storeOwnerID;
    public String storeName;

    public Integer getStoreOwnerID() {
        return storeOwnerID;
    }

    public void setStoreOwnerID(Integer storeOwnerID) {
        this.storeOwnerID = storeOwnerID;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }
}
