package com.dev.customer.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dev.customer.dto.request.WishlistRequest;
import com.dev.customer.entity.Customer;
import com.dev.customer.entity.Wishlist;
import com.dev.customer.feign.ProductClient;
import com.dev.customer.repository.WishlistRepository;
import com.dev.customer.service.CustomerService;
import com.dev.customer.service.WishlistService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@AllArgsConstructor @NoArgsConstructor
public class WishlistServiceImpl implements WishlistService {

    private WishlistRepository wishlistRepository;
    private CustomerService customerService;
    private ProductClient productClient;

    @Override
    public Wishlist create(WishlistRequest wishlistRequest) {
        Wishlist wishlist = new Wishlist();
        Customer customer = customerService.getById(wishlistRequest.getCustomerId());
        Long productId = productClient.getProductById(wishlistRequest.getProductId()).getBody().getId();
        wishlist.setCustomer(customer);
        wishlist.setProductId(productId);
        return wishlistRepository.save(wishlist);
    }

    @Override
    public Page<Wishlist> getAllByCustomerId(Long id, Pageable pageable) {
        return wishlistRepository.findAllByCustomer_Id(id, pageable);
    }

    @Override
    public void delete(Long id) {
        wishlistRepository.deleteById(id);
    }
}