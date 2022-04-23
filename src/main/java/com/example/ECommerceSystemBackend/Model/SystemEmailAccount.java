package com.example.ECommerceSystemBackend.Model;

public class SystemEmailAccount {
    private String emailAddress;
    private String emailPassword;

    public SystemEmailAccount(String emailAddress, String emailPassword){
        this.emailAddress = emailAddress;
        this.emailPassword = emailPassword;
    }
    public String getEmailAddress() {
        return emailAddress;
    }
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    public String getEmailPassword() {
        return emailPassword;
    }
    public void setEmailPassword(String emailPassword) {
        this.emailPassword = emailPassword;
    }
}
