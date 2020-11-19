package com.raczkowski.springintro.backdoor.service;

import com.raczkowski.springintro.backdoor.dto.CustomerDto;
import com.raczkowski.springintro.backdoor.dto.OrderDto;
import com.raczkowski.springintro.backdoor.exception.CustomerNotFoundException;
import com.raczkowski.springintro.backdoor.exception.OrderNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private List<CustomerDto> customers;

    public CustomerService() {
        this.customers = initializeCustomers();
    }

    public List<CustomerDto> getCustomers() {
        return customers;
    }

    public CustomerDto getCustomerForId(Long id) {
        return customers.stream()
                .filter(customer -> customer.getId().equals(id))
                .findAny()
                .orElseThrow(() -> new CustomerNotFoundException(id));
    }

    public void addCustomer(CustomerDto customerDto) {
        customers.add(customerDto);
    }

    public void removeCustomer(Long id) {
        customers = customers.stream()
                .filter(customer -> !customer.getId().equals(id))
                .collect(Collectors.toList());
    }

    public void updateCustomer(Long id, CustomerDto customerDto) {
        CustomerDto customer = getCustomerForId(id);

        customer.setId(customerDto.getId());
        customer.setAddress(customerDto.getAddress());
        customer.setName(customerDto.getName());
        customer.setOrders(customerDto.getOrders());
    }

    public List<OrderDto> getOrdersForCustomer(Long id) {
        return getCustomerForId(id).getOrders();
    }

    public OrderDto getSpecificOrderForGivenCustomer(Long customerId, Long orderId) {
        List<OrderDto> ordersForCustomer = getOrdersForCustomer(customerId);

        return ordersForCustomer.stream()
                .filter(order -> order.getId().equals(orderId))
                .findAny()
                .orElseThrow(() -> new OrderNotFoundException(orderId));
    }

    private List<CustomerDto> initializeCustomers() {
        List<CustomerDto> customers = new ArrayList<>();
        customers.add(new CustomerDto(1L,
                "Przemek",
                "Krakow",
                Arrays.asList(new OrderDto(1L, "TV"),
                        new OrderDto(2L, "Book"))));

        customers.add(new CustomerDto(2L, "Tomek",
                "Lublin",
                Arrays.asList(new OrderDto(3L, "TV"),
                        new OrderDto(4L, "Book"))));

        customers.add(
                new CustomerDto(3L,
                        "Czarek",
                        "Warszawa",
                        Arrays.asList(new OrderDto(5L, "TV"),
                                new OrderDto(6L, "Book"))));
        customers.add(
                new CustomerDto(4L,
                        "Franek",
                        "Poznań",
                        Arrays.asList(new OrderDto(7L, "TV"),
                                new OrderDto(8L, "Book"))));
        customers.add(new CustomerDto(5L,
                "Jerzy",
                "Bochnia",
                Arrays.asList(new OrderDto(9L, "TV"),
                        new OrderDto(10L, "Book"))));
        return customers;
    }
}
