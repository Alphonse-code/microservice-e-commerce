package com.dev.customer.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dev.customer.dto.request.WishlistRequest;
import com.dev.customer.entity.Wishlist;

public interface WishlistService {
    Wishlist create(WishlistRequest wishlist);
    Page<Wishlist> getAllByCustomerId(Long id, Pageable pageable);
    void delete(Long id);
}
