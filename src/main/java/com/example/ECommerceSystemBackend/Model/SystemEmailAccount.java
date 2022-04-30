package com.example.ECommerceSystemBackend.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SystemEmailAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "PASSWORD")
    private String password;

    public SystemEmailAccount() {}

    public SystemEmailAccount(String emailAddress, String emailPassword){
        this.email = emailAddress;
        this.password = emailPassword;
    }

    public String getEmailAddress() {
        return email;
    }

    public void setEmailAddress(String emailAddress) {
        this.email = emailAddress;
    }

    public String getEmailPassword() {
        return password;
    }
    
    public void setEmailPassword(String emailPassword) {
        this.password = emailPassword;
    }
}
