package com.dev.customer.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.dev.commons.exceptions.NotFoundException;
import com.dev.commons.utils.UpdatingUtil;
import com.dev.customer.entity.Customer;
import com.dev.customer.entity.ShippingAddress;
import com.dev.customer.repository.ShippingAddressServiceRepository;
import com.dev.customer.service.ShippingAddressService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@AllArgsConstructor @NoArgsConstructor
public class ShippingAddressServiceImpl implements ShippingAddressService {

    private ShippingAddressServiceRepository shippingAddressServiceRepository;
    private CustomerServiceImpl customerService;

    @Override
    public ShippingAddress createShippingAddress(Long customerId, ShippingAddress address) {
        Customer customer = customerService.getById(customerId);
        address.setCustomer(customer);
        return shippingAddressServiceRepository.save(address);
    }

    @Override
    public ShippingAddress getShippingAddressById(Long id) {
        return shippingAddressServiceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Shipping address not found"));
    }

    @Override
    public List<ShippingAddress> getAllShippingAddressesByCustomerId(Long customerId) {
        return shippingAddressServiceRepository.findAllByCustomer_Id(customerId);
    }

    @Override
    public ShippingAddress updateShippingAddress(Long id, ShippingAddress addressDetails) {
        ShippingAddress address = getShippingAddressById(id);
        BeanUtils.copyProperties(addressDetails, address, UpdatingUtil.getNullPropertyNames(addressDetails));
        return shippingAddressServiceRepository.save(address);
    }

    @Override
    public void deleteShippingAddress(Long id) {
        shippingAddressServiceRepository.deleteById(id);
    }
}
