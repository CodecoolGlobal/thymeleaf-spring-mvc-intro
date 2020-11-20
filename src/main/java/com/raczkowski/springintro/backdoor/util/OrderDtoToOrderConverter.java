package com.raczkowski.springintro.backdoor.util;

import com.raczkowski.springintro.backdoor.dto.OrderDto;
import com.raczkowski.springintro.backdoor.entity.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderDtoToOrderConverter {

    public Order convert(OrderDto orderDto) {
        return new Order(orderDto.getId(),
                orderDto.getProduct(),
                orderDto.getTotalPrice());
    }

    public List<Order> convertAll(List<OrderDto> orderDtos) {
        return orderDtos.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }
}
