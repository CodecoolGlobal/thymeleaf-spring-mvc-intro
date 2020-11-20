package com.raczkowski.springintro.backdoor.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class CustomerDto {

    private Long id;
    private String name;
    private String address;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date registrationDate;
    @JsonIgnore
    private List<OrderDto> orders;

    public CustomerDto() {
    }

    public CustomerDto(Long id,
                       String name,
                       String address,
                       Date registrationDate,
                       List<OrderDto> orders) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.registrationDate = registrationDate;
        this.orders = orders;
    }

    @JsonCreator
    public CustomerDto(@JsonProperty("name") String name,
                       @JsonProperty("address") String address,
                       @JsonProperty("registrationDate") String registrationDate) throws ParseException {

        this.name = name;
        this.address = address;
        this.registrationDate = formatDate(registrationDate);
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

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public List<OrderDto> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderDto> orders) {
        this.orders = orders;
    }

    private Date formatDate(String date) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return simpleDateFormat.parse(date);
    }
}
