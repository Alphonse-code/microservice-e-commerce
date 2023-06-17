package com.dev.customer.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.dev.commons.mapper.BasicMapper;
import com.dev.customer.dto.request.ShippingAddressRequest;
import com.dev.customer.dto.response.ShippingAddressResponse;
import com.dev.customer.entity.ShippingAddress;
import com.dev.customer.service.ShippingAddressService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Component
@AllArgsConstructor @NoArgsConstructor
public class ShippingAddressMapper {

    private BasicMapper basicMapper;
    private ShippingAddressService shippingAddressService;

    public ShippingAddressResponse createShippingAddress(ShippingAddressRequest address) {
        ShippingAddress shippingAddress = basicMapper.convertTo(address, ShippingAddress.class);
        return basicMapper.convertTo(
                shippingAddressService.createShippingAddress(address.getCustomerId(), shippingAddress),
                ShippingAddressResponse.class
        );
    }

    public ShippingAddressResponse getShippingAddressById(Long id) {
        return basicMapper.convertTo(shippingAddressService.getShippingAddressById(id), ShippingAddressResponse.class);
    }

    public List<ShippingAddressResponse> getAllShippingAddressesByCustomerId(Long customerId) {
        return basicMapper.convertListTo(shippingAddressService.getAllShippingAddressesByCustomerId(customerId), ShippingAddressResponse.class);
    }

    public ShippingAddressResponse updateShippingAddress(Long id, ShippingAddressRequest addressDetails) {
        ShippingAddress shippingAddress = basicMapper.convertTo(addressDetails, ShippingAddress.class);
        return basicMapper.convertTo(shippingAddressService.updateShippingAddress(id, shippingAddress), ShippingAddressResponse.class);
    }

    public void deleteShippingAddress(Long id) {
        shippingAddressService.deleteShippingAddress(id);
    }
}
