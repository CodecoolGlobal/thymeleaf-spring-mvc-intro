package com.raczkowski.springintro.backdoor.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Entity(name = "CUSTOMERS")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String address;
    @Temporal(TemporalType.DATE)
    private Date registrationDate;

    @OneToMany(cascade = ALL)
    private List<Order> orders;

    public Customer() {
    }

    public Customer(String name,
                    String address,
                    Date registrationDate,
                    List<Order> orders) {
        this.name = name;
        this.address = address;
        this.registrationDate = registrationDate;
        this.orders = orders;
    }

    public Customer(Long id,
                    String name,
                    String address,
                    Date registrationDate,
                    List<Order> orders) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.registrationDate = registrationDate;
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

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
