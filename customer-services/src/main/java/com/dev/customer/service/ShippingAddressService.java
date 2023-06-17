package com.dev.customer.service;

import java.util.List;

import com.dev.customer.entity.ShippingAddress;

public interface ShippingAddressService {
    ShippingAddress createShippingAddress(Long customerId, ShippingAddress address);
    ShippingAddress getShippingAddressById(Long id);
    List<ShippingAddress> getAllShippingAddressesByCustomerId(Long customerId);
    ShippingAddress updateShippingAddress(Long id, ShippingAddress addressDetails);
    void deleteShippingAddress(Long id);
}
