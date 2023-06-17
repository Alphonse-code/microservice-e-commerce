package com.dev.auth.feign;

import com.dev.auth.dto.response.CustomerResponse;
import com.dev.commons.configuration.FeignClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import static com.dev.commons.constants.FeignConstants.CUSTOMER_SERVICE;
import static com.dev.commons.constants.PathConstants.API_V1_CUSTOMER;

@FeignClient(name = CUSTOMER_SERVICE, configuration = FeignClientConfiguration.class)
public interface CustomerClient {
    @GetMapping(API_V1_CUSTOMER + "/username/{username}")
    ResponseEntity<CustomerResponse> getByUsername(@PathVariable("username") String username);
}
