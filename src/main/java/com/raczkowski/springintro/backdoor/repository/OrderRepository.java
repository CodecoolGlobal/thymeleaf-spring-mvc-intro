package com.raczkowski.springintro.backdoor.repository;

import com.raczkowski.springintro.backdoor.entity.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
