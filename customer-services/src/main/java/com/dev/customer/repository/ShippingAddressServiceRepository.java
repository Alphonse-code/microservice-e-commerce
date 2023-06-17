package com.dev.customer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.customer.entity.ShippingAddress;

public interface ShippingAddressServiceRepository extends JpaRepository<ShippingAddress, Long> {
    List<ShippingAddress> findAllByCustomer_Id(Long customerId);
}
