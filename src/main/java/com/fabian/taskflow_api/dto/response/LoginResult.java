package com.fabian.taskflow_api.dto.response;

public record LoginResult(
        LoginResponse loginResponse,
        String refreshToken
) {
}
