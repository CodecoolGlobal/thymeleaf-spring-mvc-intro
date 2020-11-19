package com.raczkowski.springintro.backdoor.service;

import com.raczkowski.springintro.backdoor.dto.CustomerDto;
import com.raczkowski.springintro.backdoor.dto.OrderDto;
import com.raczkowski.springintro.backdoor.exception.CustomerNotFoundException;
import com.raczkowski.springintro.backdoor.exception.OrderNotFoundException;
import com.raczkowski.springintro.backdoor.util.DateComparator;
import com.raczkowski.springintro.backdoor.util.SortType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

import static com.raczkowski.springintro.backdoor.util.SortType.DATE;
import static java.util.Arrays.asList;

@Service
public class CustomerService {

    private DateComparator dateComparator;
    private List<CustomerDto> customers;


    public CustomerService(DateComparator dateComparator) {
        this.dateComparator = dateComparator;
        this.customers = initializeCustomers();
    }

    public List<CustomerDto> getCustomers() {
        return customers;
    }

    public List<CustomerDto> getSortedCustomers(SortType sortType) {
        if (sortType.equals(DATE)) {
            return customers.stream()
                    .sorted((c1, c2) -> dateComparator.compare(c1.getRegistrationDate(),
                            c2.getRegistrationDate()))
                    .collect(Collectors.toList());
        }

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
                new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime(),
                asList(new OrderDto(1L, "TV"),
                        new OrderDto(2L, "Book"))));

        customers.add(new CustomerDto(2L, "Tomek",
                "Lublin",
                new GregorianCalendar(2018, Calendar.JANUARY, 11).getTime(),
                asList(new OrderDto(3L, "TV"),
                        new OrderDto(4L, "Book"))));

        customers.add(
                new CustomerDto(3L,
                        "Czarek",
                        "Warszawa",
                        new GregorianCalendar(1998, Calendar.DECEMBER, 11).getTime(),
                        asList(new OrderDto(5L, "TV"),
                                new OrderDto(6L, "Book"))));
        customers.add(
                new CustomerDto(4L,
                        "Franek",
                        "Poznań",
                        new GregorianCalendar(2012, Calendar.OCTOBER, 11).getTime(),
                        asList(new OrderDto(7L, "TV"),
                                new OrderDto(8L, "Book"))));
        customers.add(new CustomerDto(5L,
                "Jerzy",
                "Bochnia",
                new GregorianCalendar(2020, Calendar.JANUARY, 20).getTime(),
                asList(new OrderDto(9L, "TV"),
                        new OrderDto(10L, "Book"))));
        return customers;
    }
}
