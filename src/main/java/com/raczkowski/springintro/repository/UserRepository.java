package com.raczkowski.springintro.repository;

import com.raczkowski.springintro.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findByNameStartingWith(String prefix);
}
