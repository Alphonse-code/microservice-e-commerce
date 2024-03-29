package com.dev.customer.feign;

import static com.dev.commons.constants.FeignConstants.IMAGE_SERVICE;
import static com.dev.commons.constants.PathConstants.API_V1_IMAGE;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.dev.commons.configuration.FeignClientConfiguration;

@FeignClient(name = IMAGE_SERVICE, configuration = FeignClientConfiguration.class)
public interface ImageClient {
    @PostMapping(value=API_V1_IMAGE + "/upload",consumes = {"multipart/form-data"})
    String uploadImage(@RequestPart("file") MultipartFile file);
}
