package com.example.ECommerceSystemBackend.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "SURNAME")
    private String surname;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "MFA")
    private boolean mfa = false;
    @Column(name = "SECRET")
    private String secret = "";
    @Transient
    private List<Product> basket;
    @Transient
    private Integer canLogin;

    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    private List<CustomerOrder> orders;

    @OneToMany(mappedBy = "customer")
    private List<Comment> comments;

    public Customer() {}

    public Customer(String name, String surname, String email, String password) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.orders = new ArrayList<>();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void addOrder(CustomerOrder customerOrder) { this.orders.add(customerOrder); }

    public Integer getCanLogin() {
        return canLogin;
    }

    public void setCanLogin(Integer canLogin) {
        this.canLogin = canLogin;
    }

    public Integer getId() {
        return id;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Product> getBasket() {
        return basket;
    }

    public void setBasket(List<Product> basket) {
        this.basket = basket;
    }

    public String getSecret() {
        return secret;
    }

    public List<CustomerOrder> getOrders() {
        return orders;
    }

    public void setOrders(List<CustomerOrder> orders) {
        this.orders = orders;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
    
    public boolean isMfa() {
        return mfa;
    }

    public void setMfa(boolean mfa) {
        this.mfa = mfa;
    }

    public void addProduct(Product product) {}
    public void removeProduct(Product product) {}
    public void checkout() {}
    public void addFunds() {}

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
