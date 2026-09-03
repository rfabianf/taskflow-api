package com.fabian.taskflow_api.repository;

import com.fabian.taskflow_api.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {

    Optional<Role> findByNombreRol(String nombre);
}
