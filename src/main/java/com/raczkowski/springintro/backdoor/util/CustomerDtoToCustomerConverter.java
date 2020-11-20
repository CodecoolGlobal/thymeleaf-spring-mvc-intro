package com.raczkowski.springintro.backdoor.util;

import com.raczkowski.springintro.backdoor.dto.CustomerDto;
import com.raczkowski.springintro.backdoor.entity.Customer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerDtoToCustomerConverter {

    private OrderDtoToOrderConverter orderConverter;

    public CustomerDtoToCustomerConverter(OrderDtoToOrderConverter orderConverter) {
        this.orderConverter = orderConverter;
    }

    public Customer convert(CustomerDto customerDto) {
        return new Customer(customerDto.getId(),
                customerDto.getName(),
                customerDto.getAddress(),
                customerDto.getRegistrationDate(),
                orderConverter.convertAll(customerDto.getOrders()));
    }

    public List<Customer> convertAll(List<CustomerDto> customerDtos) {
        return customerDtos.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }
}
