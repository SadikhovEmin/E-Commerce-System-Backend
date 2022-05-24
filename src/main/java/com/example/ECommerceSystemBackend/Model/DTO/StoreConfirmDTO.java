package com.example.ECommerceSystemBackend.Model.DTO;

public class StoreConfirmDTO {

    public Integer storeId;
    public String storeConfirmationType;

    public StoreConfirmDTO(Integer storeId, String storeConfirmationType) {
        this.storeId = storeId;
        this.storeConfirmationType = storeConfirmationType;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getStoreConfirmationType() {
        return storeConfirmationType;
    }

    public void setStoreConfirmationType(String storeConfirmationType) {
        this.storeConfirmationType = storeConfirmationType;
    }
}
