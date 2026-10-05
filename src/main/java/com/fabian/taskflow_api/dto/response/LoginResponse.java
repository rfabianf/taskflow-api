package com.fabian.taskflow_api.dto.response;

public record LoginResponse(
		boolean registered,
        String accessToken,
        String tokenType,
		String email,
		String nombre,
		String apellidoPaterno
) {}