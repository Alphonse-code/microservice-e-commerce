package com.dev.customer.mapper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.dev.commons.mapper.BasicMapper;
import com.dev.customer.dto.request.CustomerRequest;
import com.dev.customer.dto.response.CustomerResponse;
import com.dev.customer.entity.Customer;
import com.dev.customer.service.CustomerService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Component
@AllArgsConstructor @NoArgsConstructor
public class CustomerMapper {

    private BasicMapper basicMapper;
    private CustomerService customerService;

    public CustomerResponse registration(CustomerRequest customerRequest) {
        Customer customer = basicMapper.convertTo(customerRequest, Customer.class);
        return basicMapper.convertTo(customerService.registration(customer), CustomerResponse.class);
    }

    public CustomerResponse uploadImage(MultipartFile image, Long customerId) {
        return basicMapper.convertTo(customerService.uploadImage(image, customerId), CustomerResponse.class);
    }

    public Page<CustomerResponse> getAll(Pageable pageable) {
        return customerService.getAll(pageable).map(customer -> basicMapper.convertTo(customer, CustomerResponse.class));
    }
    public CustomerResponse getById(Long id) {
        return basicMapper.convertTo(customerService.getById(id), CustomerResponse.class);
    }
    public CustomerResponse getByUsername(String username) {
        return basicMapper.convertTo(customerService.getByUsername(username), CustomerResponse.class);
    }
    public void deleteById(Long id) {
        customerService.deleteById(id);
    }
}
