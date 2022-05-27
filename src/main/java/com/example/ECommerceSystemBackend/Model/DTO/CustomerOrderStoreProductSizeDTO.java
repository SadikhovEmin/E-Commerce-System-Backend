package com.example.ECommerceSystemBackend.Model.DTO;

public class CustomerOrderStoreProductSizeDTO {

    public String storeName;
    public Integer customerOrderProductSize;

    public CustomerOrderStoreProductSizeDTO(String storeName, Integer customerOrderProductSize) {
        this.storeName = storeName;
        this.customerOrderProductSize = customerOrderProductSize;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Integer getCustomerOrderProductSize() {
        return customerOrderProductSize;
    }

    public void setCustomerOrderProductSize(Integer customerOrderProductSize) {
        this.customerOrderProductSize = customerOrderProductSize;
    }
}
