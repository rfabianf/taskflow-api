package com.fabian.taskflow_api.dto.response;

import jakarta.persistence.Column;

public record UserResponse(
    String nombre,
    String apellidoPaterno,
    String apellidoMaterno,
    String email
) {}
