package com.example.ECommerceSystemBackend.Model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "store_owner_id")
    private Integer storeOwnerId;
    @Column(name = "customer_id")
    private Integer customerId;
    @ElementCollection
    @CollectionTable(name = "ids_of_products")
    private List<Integer> idsOfProducts = new ArrayList<Integer>();
    @ElementCollection
    @CollectionTable(name="quantities_of_products")
    private  List<Integer> quantitiesOfProducts = new ArrayList<Integer>();
    
    public Integer getStoreOwnerId() {
        return storeOwnerId;
    }
    public void setStoreOwnerId(Integer storeOwnerId) {
        this.storeOwnerId = storeOwnerId;
    }
    public Integer getCustomerId() {
        return customerId;
    }
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
    public List<Integer> getIdsOfProducts() {
        return idsOfProducts;
    }
    public void setIdsOfProducts(List<Integer> idsOfProducts) {
        this.idsOfProducts = idsOfProducts;
    }
    public List<Integer> getQuantitiesOfProducts() {
        return quantitiesOfProducts;
    }
    public void setQuantitiesOfProducts(List<Integer> quantitiesOfProducts) {
        this.quantitiesOfProducts = quantitiesOfProducts;
    }
    
    
}
