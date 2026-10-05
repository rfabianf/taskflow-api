package com.fabian.taskflow_api.dto.response;
import java.util.UUID;

public record CurrentUserResponse(
    UUID id,
    String email,
    String nombre,
    String apellidoPattern,
    String apellidoMaterno,
    String nombreRol
){
    @Override
    public UUID id() {
        return id;
    }

    @Override
    public String email() {
        return email;
    }

    @Override
    public String nombre() {
        return nombre;
    }

    @Override
    public String apellidoPattern() {
        return apellidoPattern;
    }

    @Override
    public String apellidoMaterno() {
        return apellidoMaterno;
    }

    @Override
    public String nombreRol() {
        return nombreRol;
    }
}
