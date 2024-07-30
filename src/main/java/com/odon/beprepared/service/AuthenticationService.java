package com.odon.beprepared.service;

import com.odon.beprepared.dto.request.AuthenticationRequestForUser;
import com.odon.beprepared.dto.response.TokenResponse;

public interface AuthenticationService {
    TokenResponse authenticate(AuthenticationRequestForUser authenticationRequestForUser);
}
