package com.odon.beprepared.service;

import com.odon.beprepared.dto.response.StatsResponse;
import com.odon.beprepared.model.User;

public interface UserService {

    String createUser(User user);

    User getUserById(Long id);

    StatsResponse getAllStats();
}
