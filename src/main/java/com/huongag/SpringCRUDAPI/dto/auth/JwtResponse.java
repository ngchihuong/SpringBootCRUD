package com.huongag.SpringCRUDAPI.dto.auth;


import com.huongag.SpringCRUDAPI.entity.AccountEntity;

public class JwtResponse {
private String token;
private String type = "Bearer";
private Integer id;
private String email;
private String name;
private String address;

    public JwtResponse() {
    }

    public JwtResponse(String token, AccountEntity account) {
        this.token = token;
        this.id = account.getId();
        this.name = account.getName();
        this.email = account.getEmail();
        this.address = account.getAddress();
    }

    public JwtResponse(String token, String type, Integer id, String email, String name, String address) {
        this.token = token;
        this.type = type;
        this.id = id;
        this.email = email;
        this.name = name;
        this.address = address;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
