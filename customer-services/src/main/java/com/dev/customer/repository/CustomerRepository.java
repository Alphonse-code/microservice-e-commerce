package com.dev.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.customer.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByUsername(String username);
}
