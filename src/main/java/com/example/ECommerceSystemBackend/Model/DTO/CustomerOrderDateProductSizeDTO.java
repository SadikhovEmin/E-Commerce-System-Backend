package com.example.ECommerceSystemBackend.Model.DTO;

import java.util.Date;

public class CustomerOrderDateProductSizeDTO {

    public Date customerOrderDate;
    public Integer customerOrderProductSize;

    public CustomerOrderDateProductSizeDTO(Date customerOrderDate, Integer customerOrderProductSize) {
        this.customerOrderDate = customerOrderDate;
        this.customerOrderProductSize = customerOrderProductSize;
    }

    public Date getCustomerOrderDate() {
        return customerOrderDate;
    }

    public void setCustomerOrderDate(Date customerOrderDate) {
        this.customerOrderDate = customerOrderDate;
    }

    public Integer getCustomerOrderProductSize() {
        return customerOrderProductSize;
    }

    public void setCustomerOrderProductSize(Integer customerOrderProductSize) {
        this.customerOrderProductSize = customerOrderProductSize;
    }
}
