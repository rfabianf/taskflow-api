package com.fabian.taskflow_api.repository;

import com.fabian.taskflow_api.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    boolean existsByEmail(String email);
    @EntityGraph(attributePaths = "rol")
    Optional<User> findByEmail(String email);
}
