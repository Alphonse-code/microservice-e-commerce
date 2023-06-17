package com.dev.order.mapper;

import com.dev.order.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderItemMapper {

    private final OrderItemService orderItemService;

    public void deleteOrderItem(Long id) {
        orderItemService.deleteOrderItem(id);
    }
}
