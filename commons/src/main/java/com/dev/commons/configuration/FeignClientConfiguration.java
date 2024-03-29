package com.dev.commons.configuration;

import feign.RequestInterceptor;
import feign.codec.ErrorDecoder;
import com.dev.commons.decoder.CustomErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignClientConfiguration {
    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {};
    }

    @Bean
    public ErrorDecoder errorDecoder() {
        return new CustomErrorDecoder();
    }
}
