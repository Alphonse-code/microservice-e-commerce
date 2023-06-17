package com.dev.customer.feign;

import static com.dev.commons.constants.FeignConstants.AUTH_SERVICE;
import static com.dev.commons.constants.PathConstants.API_V1_AUTH;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.dev.commons.configuration.FeignClientConfiguration;
import com.dev.commons.dto.authentication.AuthenticationRequest;

@FeignClient(value = AUTH_SERVICE, configuration = FeignClientConfiguration.class)
public interface AuthClient {
    @PostMapping(API_V1_AUTH)
    void authenticate(@RequestBody AuthenticationRequest authenticationRequest);
}
