package com.example.ECommerceSystemBackend.Model.DTO;

import com.example.ECommerceSystemBackend.Model.Product;
import com.example.ECommerceSystemBackend.Model.enums.OrderStatus;

import java.util.Date;
import java.util.List;

public class OrderActivityDTO {
    public Integer orderID;
    public Date date;
    public Double price;
    public OrderStatus status;

    public OrderActivityDTO(Integer orderID, Date date, Double price, OrderStatus status) {
        this.orderID = orderID;
        this.date = date;
        this.price = price;
        this.status = status;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
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
