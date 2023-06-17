package com.dev.order.service;

import com.dev.order.entity.OrderItem;

public interface OrderItemService {
    OrderItem create(OrderItem orderItem);
    OrderItem updateOrderItem(Long id, OrderItem updatedOrderItem);
    void deleteOrderItem(Long id);
}