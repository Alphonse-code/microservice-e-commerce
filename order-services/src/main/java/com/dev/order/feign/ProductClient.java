package com.dev.order.feign;

import com.dev.commons.configuration.FeignClientConfiguration;
import com.dev.commons.dto.catalog.product.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import static com.dev.commons.constants.FeignConstants.CATALOG_SERVICE;
import static com.dev.commons.constants.PathConstants.API_V1_PRODUCT;

@FeignClient(value = CATALOG_SERVICE, configuration = FeignClientConfiguration.class)
public interface ProductClient {
    @GetMapping(API_V1_PRODUCT + "/{productId}")
    ResponseEntity<ProductResponse> getProductById(@PathVariable("productId") Long productId);

    @PutMapping(API_V1_PRODUCT + "/{productId}/orders/count/{increase}")
    void updateOrdersCount(@PathVariable("productId") Long productId, @PathVariable("increase") boolean increase);
}
