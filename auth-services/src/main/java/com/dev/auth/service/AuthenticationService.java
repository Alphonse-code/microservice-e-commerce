package com.dev.auth.service;

import com.dev.commons.dto.authentication.AuthenticationRequest;
import com.dev.commons.dto.authentication.AuthenticationResponse;

public interface AuthenticationService {
    AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);
}
