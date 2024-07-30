package com.odon.beprepared.service.impl;

import com.odon.beprepared.dto.request.AuthenticationRequestForUser;
import com.odon.beprepared.dto.response.TokenResponse;
import com.odon.beprepared.model.Token;
import com.odon.beprepared.model.User;
import com.odon.beprepared.repository.TokenRepository;
import com.odon.beprepared.repository.UserRepository;
import com.odon.beprepared.security.JWTService;
import com.odon.beprepared.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final JWTService jwtService;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final AuthenticationManager authenticationManager;

    @Override
    @Transactional
    public TokenResponse authenticate(AuthenticationRequestForUser authenticationRequestForUser) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequestForUser.getEmail(),
                        authenticationRequestForUser.getPassword()
                )
        );
        var user = userRepository.findByEmail(authenticationRequestForUser.getEmail()).orElseThrow();
        var token = jwtService.generateToken(user);
        saveUserToken(user, token);
        return TokenResponse.builder()
                .acessToken(token)
                .build();
    }
    private void saveUserToken(User user, String token){
        var jwtToken = Token.builder()
                .user(user)
                .token(token)
                .expired(false)
                .revoked(false)
                .createdAt(LocalDateTime.now())
                .build();
        tokenRepository.save(jwtToken);
    }
}
