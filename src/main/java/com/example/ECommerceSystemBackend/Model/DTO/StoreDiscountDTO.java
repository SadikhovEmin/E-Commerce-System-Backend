package com.example.ECommerceSystemBackend.Model.DTO;

public class StoreDiscountDTO {

    public Integer storeId;
    public Integer discountPercentage;

    public StoreDiscountDTO(Integer storeId, Integer discountPercentage) {
        this.storeId = storeId;
        this.discountPercentage = discountPercentage;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(Integer discountPercentage) {
        this.discountPercentage = discountPercentage;
    }
}
