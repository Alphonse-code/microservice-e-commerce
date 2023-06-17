package com.dev.order.mapper;

import com.dev.commons.dto.cart.CartItemResponse;
import com.dev.commons.mapper.BasicMapper;
import com.dev.order.dto.request.OrderRequest;
import com.dev.order.dto.response.OrderItemResponse;
import com.dev.order.dto.response.OrderResponse;
import com.dev.order.entity.Order;
import com.dev.order.entity.OrderItem;
import com.dev.order.service.OrderItemService;
import com.dev.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderMapper {

    private final BasicMapper basicMapper;
    private final OrderService orderService;
    private final OrderItemService orderItemService;

    public OrderResponse create(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderDescription(orderRequest.getOrderDescription());
        order.setOrderStatus(orderRequest.getOrderStatus());
        order.setCustomerId(orderRequest.getCustomerId());

        for(CartItemResponse cartItem : orderRequest.getCartItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setProductId(cartItem.getProductId());
            orderItem.setPrice(cartItem.getPrice());
            order.increaseTotalPrice(cartItem.getPrice());
            order.addItem(orderItemService.create(orderItem));
        }

        return basicMapper.convertTo(orderService.create(order), OrderResponse.class);
    }
    public Page<OrderResponse> getAll(Pageable pageable) {
        return orderService.getAll(pageable).map(order -> basicMapper.convertTo(order, OrderResponse.class));
    }
    public OrderResponse getOrderById(Long id) {
        return basicMapper.convertTo(orderService.getOrderById(id), OrderResponse.class);
    }
    public Page<OrderItemResponse> getOrderItemsByOrder(Long orderId, Pageable pageable) {
        return orderService.getOrderItemsByOrder(orderId, pageable)
                .map(orderItem -> basicMapper.convertTo(orderItem, OrderItemResponse.class));
    }
    public Page<OrderResponse> getOrdersByCustomer(Long customerId, Pageable pageable) {
        return orderService.getOrdersByCustomer(customerId, pageable).map(order -> basicMapper.convertTo(order, OrderResponse.class));
    }
    public OrderResponse updateOrder(Long id, OrderRequest updatedOrder) {
        Order order = basicMapper.convertTo(updatedOrder, Order.class);
        return basicMapper.convertTo(orderService.updateOrder(id, order), OrderResponse.class);
    }
    public void deleteOrder(Long id) {
        orderService.deleteOrder(id);
    }
}
