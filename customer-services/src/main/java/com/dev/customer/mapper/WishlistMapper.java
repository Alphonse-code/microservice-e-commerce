package com.dev.customer.mapper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.dev.commons.mapper.BasicMapper;
import com.dev.customer.dto.request.WishlistRequest;
import com.dev.customer.dto.response.WishlistResponse;
import com.dev.customer.service.WishlistService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Component
@AllArgsConstructor @NoArgsConstructor
public class WishlistMapper {

    private BasicMapper basicMapper;
    private WishlistService wishlistService;

    public WishlistResponse create(WishlistRequest wishlistRequest) {
        return basicMapper.convertTo(wishlistService.create(wishlistRequest), WishlistResponse.class);
    }

    public Page<WishlistResponse> getAllByCustomerId(Long customerId, Pageable pageable) {
        return wishlistService.getAllByCustomerId(customerId, pageable).map(w -> basicMapper.convertTo(w, WishlistResponse.class));
    }

    public void deleteById(Long id) {
        wishlistService.delete(id);
    }
}
