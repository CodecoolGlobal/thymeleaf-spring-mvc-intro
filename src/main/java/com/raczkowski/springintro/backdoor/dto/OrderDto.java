package com.raczkowski.springintro.backdoor.dto;

public class OrderDto {

    private Long id;
    private String product;
    private Integer totalPrice;

    public OrderDto() {
    }

    public OrderDto(Long id, String product, Integer totalPrice) {
        this.id = id;
        this.product = product;
        this.totalPrice = totalPrice;
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

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }
}
