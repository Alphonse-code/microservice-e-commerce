package com.dev.customer.validator;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.dev.customer.repository.CustomerRepository;
import com.dev.customer.validator.constraint.UsernameConstraint;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UsernameValidator implements ConstraintValidator<UsernameConstraint, String> {

    private final CustomerRepository customerRepository;
    private static final Pattern USERNAME_PATTERN = Pattern.compile("^[A-Za-z][A-Za-z0-9_]{7,29}$");

    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        if ((username == null) || !USERNAME_PATTERN.matcher(username).matches() || (customerRepository.findByUsername(username) != null)) {
            return false;
        }

        return true;
    }
}
