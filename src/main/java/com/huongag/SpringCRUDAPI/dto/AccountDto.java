package com.huongag.SpringCRUDAPI.dto;

public class AccountDto {
    private int id;
    private String name;
    private String email;
    private int age;
    private String address;

    public AccountDto(int id, String name, String email, int age, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }
}
