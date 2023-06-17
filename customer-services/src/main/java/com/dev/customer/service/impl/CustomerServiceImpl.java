package com.dev.customer.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dev.commons.dto.authentication.AuthenticationRequest;
import com.dev.commons.dto.cart.CartRequest;
import com.dev.commons.exceptions.AlreadyExistException;
import com.dev.commons.exceptions.NotFoundException;
import com.dev.customer.entity.Customer;
import com.dev.customer.feign.AuthClient;
import com.dev.customer.feign.CartClient;
import com.dev.customer.feign.ImageClient;
import com.dev.customer.repository.CustomerRepository;
import com.dev.customer.service.CustomerService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@AllArgsConstructor @NoArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;
    private ImageClient imageClient;
    private AuthClient authClient;
    private CartClient cartClient;

    @Override
    public Customer registration(Customer customer) {
        String password = customer.getPassword();
        customer.setPassword(new BCryptPasswordEncoder().encode(password));
        if(customerRepository.findByUsername(customer.getUsername()) != null) {
            throw new AlreadyExistException("Username is already taken.");
        }
        Customer c = customerRepository.save(customer);
        authClient.authenticate(new AuthenticationRequest(customer.getUsername(), password));
        cartClient.createCart(new CartRequest(c.getId()));
        return c;
    }

    @Override
    public Customer uploadImage(MultipartFile image, Long customerId) {
        Customer customer = getById(customerId);
        if(image != null && !image.isEmpty()) {
            String imageUrl = imageClient.uploadImage(image);
            customer.setImageUrl(imageUrl);
        }
        return customer;
    }

    @Override
    public Page<Customer> getAll(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    @Override
    public Customer getById(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new NotFoundException("Customer not found"));
    }

    @Override
    public Customer getByUsername(String username) {
        return customerRepository.findByUsername(username);
    }

    @Override
    public void deleteById(Long id) {
        customerRepository.deleteById(id);
    }
}
