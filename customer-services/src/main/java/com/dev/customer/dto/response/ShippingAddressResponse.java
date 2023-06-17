package com.dev.customer.dto.response;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ShippingAddressResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String streetAddress;
    private String country;
    private String city;
    private String zipCode;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
