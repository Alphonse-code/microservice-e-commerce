package com.dev.customer.dto.response;

import java.time.LocalDateTime;

import com.dev.customer.enums.CustomerRole;

import lombok.Data;

@Data
public class CustomerResponse {
    private Long id;
    private String username;
    private String password;
    private CustomerRole role;
    private String firstName;
    private String lastName;
    private String imageUrl;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
