package com.example.ECommerceSystemBackend.Model.DTO;

import com.example.ECommerceSystemBackend.Model.Product;

import java.util.Date;
import java.util.List;

public class OrderActivityDTO {
    public Integer orderID;
    public Date date;
    public Double price;

    public OrderActivityDTO(Integer orderID, Date date, Double price) {
        this.orderID = orderID;
        this.date = date;
        this.price = price;
    }

    public Integer getOrderID() {
        return orderID;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
