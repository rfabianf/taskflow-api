package com.fabian.taskflow_api.repository;

import com.fabian.taskflow_api.entity.RefreshToken;
import com.fabian.taskflow_api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, UUID> {

    Optional<RefreshToken> findByToken(String token);
    void deleteByUser(User user);

}