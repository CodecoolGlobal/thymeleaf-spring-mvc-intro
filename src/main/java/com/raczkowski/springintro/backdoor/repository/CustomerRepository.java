package com.raczkowski.springintro.backdoor.repository;

import com.raczkowski.springintro.backdoor.entity.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
