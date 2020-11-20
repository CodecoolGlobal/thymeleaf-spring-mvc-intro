package com.raczkowski.springintro.backdoor.util;

import com.raczkowski.springintro.backdoor.dto.CustomerDto;
import com.raczkowski.springintro.backdoor.entity.Customer;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class CustomerToCustomerDtoConverter {

    private OrderToOrderDtoConverter orderDtoConverter;

    public CustomerToCustomerDtoConverter(OrderToOrderDtoConverter orderDtoConverter) {
        this.orderDtoConverter = orderDtoConverter;
    }

    public CustomerDto convert(Customer customer) {
        return new CustomerDto(customer.getId(),
                customer.getName(),
                customer.getAddress(),
                customer.getRegistrationDate(),
                orderDtoConverter.convertAll(customer.getOrders()));
    }

    public List<CustomerDto> convertAll(List<Customer> customers) {
        return customers.stream()
                .map(this::convert)
                .collect(toList());
    }

}
