package com.raczkowski.springintro.dto;

import java.util.Objects;

public class UserDto {
    private Long id;
    private String name;
    private String address;

    public UserDto() {
    }

    public UserDto(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public UserDto(Long id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return Objects.equals(name, userDto.name) &&
                Objects.equals(address, userDto.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, address);
    }
}
