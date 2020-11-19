package com.raczkowski.springintro.backdoor.dto;

public class OrderDto {

    private Long id;
    private String product;

    public OrderDto() {
    }

    public OrderDto(Long id, String product) {
        this.id = id;
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }
}
