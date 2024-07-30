package com.odon.beprepared.service.impl;

import com.odon.beprepared.dto.response.StatsResponse;
import com.odon.beprepared.exception.BadRequestException;
import com.odon.beprepared.exception.EntityNotFoundException;
import com.odon.beprepared.model.User;
import com.odon.beprepared.repository.AlertRepository;
import com.odon.beprepared.repository.CitizenRepository;
import com.odon.beprepared.repository.UserRepository;
import com.odon.beprepared.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AlertRepository alertRepository;
    private final CitizenRepository citizenRepository;

    @Override
    @Transactional
    public String createUser(User user) {
        if(userRepository.existsByEmail(user.getEmail())){
            throw  new BadRequestException("Ja existe um usuario com esse email");
        }
        userRepository.save(user);
        return "Usuario Criado com sucesso!";
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Usuario nao encontrado!"));
    }

    @Override
    public StatsResponse getAllStats() {
        return StatsResponse.builder()
                .citizens(citizenRepository.count())
                .totalAlerts(alertRepository.count())
                .activeAlerts(alertRepository.countByActive(true))
                .build();
    }
}
