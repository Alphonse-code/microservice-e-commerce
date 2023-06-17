package com.dev.cart.mapper;

import com.dev.commons.dto.cart.CartRequest;
import com.dev.cart.dto.request.CartItemRequest;
import com.dev.commons.dto.cart.CartResponse;
import com.dev.commons.mapper.BasicMapper;
import com.dev.cart.entity.Cart;
import com.dev.cart.entity.CartItem;
import com.dev.cart.service.CartService;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CartMapper {

	
    private BasicMapper basicMapper;
    private CartService cartService;

    
    public CartResponse create(CartRequest cartRequest) {
        Cart cart = basicMapper.convertTo(cartRequest, Cart.class);
        return basicMapper.convertTo(cartService.create(cart), CartResponse.class);
    }

    public CartResponse addItem(Long cartId, CartItemRequest cartItemRequest) {
        Cart cart = cartService.addItem(cartId, basicMapper.convertTo(cartItemRequest, CartItem.class));
        return basicMapper.convertTo(cart, CartResponse.class);
    }

    public CartResponse removeItem(Long cartId, Long itemId) {
        return basicMapper.convertTo(cartService.removeItem(cartId, itemId), CartResponse.class);
    }

    public CartResponse getById(Long id) {
        return basicMapper.convertTo(cartService.getById(id), CartResponse.class);
    }

    public CartResponse completeCart(Long id) {
        return basicMapper.convertTo(cartService.completeCart(id), CartResponse.class);
    }

    public CartResponse getByCustomerId(Long id) {
        return basicMapper.convertTo(cartService.getByCustomerId(id), CartResponse.class);
    }

    public void deleteByCustomerId(Long id) {
        cartService.deleteByCustomerId(id);
    }

	/**
	 * 
	 */
	public CartMapper() {
		super();
		// TODO Auto-generated constructor stub
	}
}
