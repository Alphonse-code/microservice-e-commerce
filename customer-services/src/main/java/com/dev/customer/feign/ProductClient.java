package com.dev.customer.feign;

import static com.dev.commons.constants.FeignConstants.CATALOG_SERVICE;
import static com.dev.commons.constants.PathConstants.API_V1_PRODUCT;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.dev.commons.configuration.FeignClientConfiguration;
import com.dev.commons.dto.catalog.product.ProductResponse;

@FeignClient(name = CATALOG_SERVICE, configuration = FeignClientConfiguration.class)
public interface ProductClient {
    @GetMapping(value=API_V1_PRODUCT + "/{productId}")
    ResponseEntity<ProductResponse> getProductById(@PathVariable("productId") Long productId);

}
