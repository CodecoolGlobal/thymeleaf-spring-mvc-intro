package com.raczkowski.springintro.backdoor.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class CustomerDto {

    private Long id;
    private String name;
    private String address;
    @JsonIgnore
    private List<OrderDto> orders;

    public CustomerDto() {
    }

    public CustomerDto(Long id,
                       String name,
                       String address,
                       List<OrderDto> orders) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.orders = orders;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<OrderDto> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderDto> orders) {
        this.orders = orders;
    }
}
