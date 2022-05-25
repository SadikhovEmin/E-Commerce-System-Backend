package com.example.ECommerceSystemBackend.Model.DTO;

import com.example.ECommerceSystemBackend.Model.enums.ConfirmationType;

public class StoreConfirmDTO {

    public Integer storeId;
    public ConfirmationType storeConfirmationType;

    public StoreConfirmDTO(Integer storeId, ConfirmationType storeConfirmationType) {
        this.storeId = storeId;
        this.storeConfirmationType = storeConfirmationType;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public ConfirmationType getStoreConfirmationType() {
        return storeConfirmationType;
    }

    public void setStoreConfirmationType(ConfirmationType storeConfirmationType) {
        this.storeConfirmationType = storeConfirmationType;
    }
}
