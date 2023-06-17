package com.dev.customer.feign;

import static com.dev.commons.constants.FeignConstants.CART_SERVICE;
import static com.dev.commons.constants.PathConstants.API_V1_CART;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.dev.commons.configuration.FeignClientConfiguration;
import com.dev.commons.dto.cart.CartRequest;

import jakarta.validation.Valid;

@FeignClient(name = CART_SERVICE, configuration = FeignClientConfiguration.class)
public interface CartClient {
    @PostMapping(value=API_V1_CART)
    ResponseEntity<Object> createCart(@Valid @RequestBody CartRequest cartRequest);
}