package com.fabian.taskflow_api.repository;

import com.fabian.taskflow_api.entity.User;
import com.fabian.taskflow_api.entity.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserCredentialRepository extends JpaRepository<UserCredential, UUID>
{
    Optional<UserCredential> findByUser(User user);
}
