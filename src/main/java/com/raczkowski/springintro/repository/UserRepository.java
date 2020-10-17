package com.raczkowski.springintro.repository;

import com.raczkowski.springintro.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
