package com.fabian.taskflow_api.dto.response;

import com.fabian.taskflow_api.entity.Role;
import jakarta.validation.constraints.Email;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class RegisterUserResponse
{
    public RegisterUserResponse() {
    }

    private UUID id;
    @Email
    private String email;
    private String nombreCompleto;
    private String nombreRol;
    private LocalDateTime fechaRegistro;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String rol) {
        this.nombreRol = rol;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}
