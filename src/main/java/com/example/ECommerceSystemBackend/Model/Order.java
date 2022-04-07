package com.example.ECommerceSystemBackend.Model;

import com.example.ECommerceSystemBackend.Model.enums.Status;

import java.util.Date;

public class Order {
    private Integer id;
    private Date date;
    private Customer customer;  // Assigned Customer -> Foreign key
    private Store store;        // Assigned Store -> Foreign key
    private Double price;
    private Status status;
}
