package com.raczkowski.springintro.model;

public class User {
    private int id;
    private String name;
    private String address;
    private String password;

    public User(String name, String address, String password) {
        this.name = name;
        this.address = address;
        this.password = password;
    }

    public User(int id, String name, String address, String password) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
