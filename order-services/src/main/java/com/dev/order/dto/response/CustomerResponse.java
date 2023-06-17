package com.dev.order.dto.response;

import com.dev.order.enums.CustomerRole;
import lombok.Data;

import java.time.LocalDateTime;

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
