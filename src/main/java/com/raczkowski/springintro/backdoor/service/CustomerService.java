package com.raczkowski.springintro.backdoor.service;

import com.raczkowski.springintro.backdoor.dto.CustomerDto;
import com.raczkowski.springintro.backdoor.dto.OrderDto;
import com.raczkowski.springintro.backdoor.entity.Customer;
import com.raczkowski.springintro.backdoor.exception.CustomerNotFoundException;
import com.raczkowski.springintro.backdoor.exception.OrderNotFoundException;
import com.raczkowski.springintro.backdoor.repository.CustomerRepository;
import com.raczkowski.springintro.backdoor.util.CustomerDtoToCustomerConverter;
import com.raczkowski.springintro.backdoor.util.CustomerToCustomerDtoConverter;
import com.raczkowski.springintro.backdoor.util.DateComparator;
import com.raczkowski.springintro.backdoor.util.SortType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.raczkowski.springintro.backdoor.util.SortType.DATE;

@Service
public class CustomerService {

    private DateComparator dateComparator;
    private CustomerRepository customerRepository;
    private CustomerToCustomerDtoConverter customerToCustomerDtoConverter;
    private CustomerDtoToCustomerConverter customerDtoToCustomerConverter;

    public CustomerService(DateComparator dateComparator,
                           CustomerRepository customerRepository,
                           CustomerToCustomerDtoConverter customerToCustomerDtoConverter,
                           CustomerDtoToCustomerConverter customerDtoToCustomerConverter) {
        this.dateComparator = dateComparator;
        this.customerRepository = customerRepository;
        this.customerToCustomerDtoConverter = customerToCustomerDtoConverter;
        this.customerDtoToCustomerConverter = customerDtoToCustomerConverter;
    }

    public List<CustomerDto> getCustomers() {
        return customerToCustomerDtoConverter.convertAll((List<Customer>) customerRepository.findAll());
    }

    public List<CustomerDto> getSortedCustomers(SortType sortType) {
        List<CustomerDto> customers = getCustomers();

        if (sortType.equals(DATE)) {
            return customers.stream()
                    .sorted((c1, c2) -> dateComparator.compare(c1.getRegistrationDate(),
                            c2.getRegistrationDate()))
                    .collect(Collectors.toList());
        }

        return customers;
    }

    public CustomerDto getCustomerForId(Long id) {
        return customerToCustomerDtoConverter.convert(
                customerRepository.findById(id)
                        .orElseThrow(() -> new CustomerNotFoundException(id)));
    }

    public void addCustomer(CustomerDto customerDto) {
        customerRepository.save(customerDtoToCustomerConverter.convert(customerDto));
    }

    public void removeCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    public void updateCustomer(Long id, CustomerDto customerDto) {
        customerRepository.save(customerDtoToCustomerConverter.convert(customerDto));
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
}
