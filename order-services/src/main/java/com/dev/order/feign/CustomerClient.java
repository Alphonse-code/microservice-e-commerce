package com.dev.order.feign;

import com.dev.commons.configuration.FeignClientConfiguration;
import com.dev.order.dto.response.CustomerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import static com.dev.commons.constants.FeignConstants.CUSTOMER_SERVICE;
import static com.dev.commons.constants.PathConstants.API_V1_CUSTOMER;

@FeignClient(value = CUSTOMER_SERVICE, configuration = FeignClientConfiguration.class)
public interface CustomerClient {
    @GetMapping(API_V1_CUSTOMER + "/{customerId}")
    ResponseEntity<CustomerResponse> getById(@PathVariable("customerId") Long id);
}
