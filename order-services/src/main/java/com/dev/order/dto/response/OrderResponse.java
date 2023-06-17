package com.dev.order.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.dev.order.enums.OrderStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderResponse {
    private Long id;
    private Long customerId;
    private String orderDescription;
    private BigDecimal orderFee;
    private OrderStatus orderStatus;
    @JsonIgnoreProperties("order")
    private List<OrderItemResponse> orderItems;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
