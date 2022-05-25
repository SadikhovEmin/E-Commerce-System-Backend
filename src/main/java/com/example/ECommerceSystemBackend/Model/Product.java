package com.example.ECommerceSystemBackend.Model;

import com.example.ECommerceSystemBackend.Model.enums.ProductType;
import javax.persistence.*;
import java.util.List;



@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "PRICE")
    private Double price;
    @Column(name = "QUANTITY")
    private Integer quantity;
    @Column(name = "TYPE")
    private ProductType type;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "REVIEW")
    private Double review;

    @Column(name = "SUSPENDED")
    private Boolean suspended;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "Shop_ID", referencedColumnName = "id")
    public Store store;

    @OneToMany(mappedBy = "product")
    public List<Comment> commentList;

    @ManyToMany(mappedBy = "products")
    public List<CustomerOrder> orders;

    public Product() {
    }

    public Product(String name, Double price, ProductType type, Store store) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.store = store;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setPriceWithDiscount(){
        this.price = this.price- ((this.price* this.store.discountPercentage)/100);
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }


    public Boolean getSuspended() {
        return suspended;
    }

    public void setSuspended(Boolean suspended) {
        this.suspended = suspended;
    }

    public Double getReview() {
        return review;
    }

    public void setReview(Double review) {
        this.review = review;

    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public List<CustomerOrder> getOrders() {
        return orders;
    }

    public void setOrders(List<CustomerOrder> orders) {
        this.orders = orders;
    }
}