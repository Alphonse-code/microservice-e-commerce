package com.dev.order.service.impl;

import com.dev.order.entity.OrderItem;
import com.dev.order.feign.ProductClient;
import com.dev.order.repository.OrderItemRepository;
import com.dev.order.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final ProductClient productClient;

    private void increaseOrderCount(Long productId) {
        productClient.updateOrdersCount(productId, true);
    }

    @Override
    public OrderItem create(OrderItem orderItem) {
        increaseOrderCount(orderItem.getProductId());
        return orderItemRepository.save(orderItem);
    }

    @Override
    public OrderItem updateOrderItem(Long id, OrderItem updatedOrderItem) {
        OrderItem orderItem = orderItemRepository.findById(id).orElseThrow();
        orderItem.setQuantity(updatedOrderItem.getQuantity());
        orderItem.setOrder(updatedOrderItem.getOrder());
        orderItem.setProductId(updatedOrderItem.getProductId());
        if(!Objects.equals(orderItem.getProductId(), updatedOrderItem.getProductId())) {
            increaseOrderCount(updatedOrderItem.getProductId());
        }
        return orderItemRepository.save(orderItem);
    }

    @Override
    public void deleteOrderItem(Long id) {
        orderItemRepository.deleteById(id);
    }
}