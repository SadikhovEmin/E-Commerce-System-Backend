package com.example.ECommerceSystemBackend.model;

import com.example.ECommerceSystemBackend.model.enums.Status;

import java.util.Date;

public class Order {
    private Integer id;
    private Date date;
    private Customer customer;  // Assigned Customer -> Foreign key
    private Store store;        // Assigned Store -> Foreign key
    private Double price;
    private Status status;
}
