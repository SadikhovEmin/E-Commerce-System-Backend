package com.example.ECommerceSystemBackend.Model.DTO;

public class PasswordDTO {

    public Integer id;
    public String password;

    public PasswordDTO(Integer id, String password) {
        this.id = id;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }
}
