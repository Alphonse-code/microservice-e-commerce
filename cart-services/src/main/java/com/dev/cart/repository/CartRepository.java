package com.dev.cart.repository;

import com.dev.cart.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
    void deleteByCustomerId(Long customerId);
    Cart findByCustomerId(Long customerId);
}
