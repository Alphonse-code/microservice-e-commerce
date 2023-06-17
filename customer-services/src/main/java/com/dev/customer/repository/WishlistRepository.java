package com.dev.customer.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.customer.entity.Wishlist;

public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
    Page<Wishlist> findAllByCustomer_Id(Long id, Pageable pageable);
}
