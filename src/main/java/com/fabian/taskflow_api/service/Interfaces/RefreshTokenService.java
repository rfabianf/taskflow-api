package com.fabian.taskflow_api.service.Interfaces;

import com.fabian.taskflow_api.entity.RefreshToken;
import com.fabian.taskflow_api.entity.User;

import java.util.Optional;

public interface RefreshTokenService {

    RefreshToken createRefreshToken(User user);
    RefreshToken verifyExpiration(RefreshToken refreshToken);
    Optional<RefreshToken> findByToken(String token);
    void deleteByUser(User user);
    void deleteByToken(String token);
}