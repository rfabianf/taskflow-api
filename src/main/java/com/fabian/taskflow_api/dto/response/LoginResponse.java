package com.fabian.taskflow_api.dto.response;

public record LoginResponse(
        String accessToken,
        String tokenType
) {}
