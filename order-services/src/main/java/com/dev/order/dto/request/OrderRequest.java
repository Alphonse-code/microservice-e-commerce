package com.dev.order.dto.request;

import com.dev.commons.dto.cart.CartResponse;
import com.dev.order.enums.OrderStatus;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data 
public class OrderRequest extends CartResponse {
	
    @Size(min = 3, max = 25, message = "Order description must be between 3 and 100 characters")
    private String orderDescription;
    private OrderStatus orderStatus;
}
