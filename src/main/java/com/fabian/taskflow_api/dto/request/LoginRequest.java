package com.fabian.taskflow_api.dto.request;

import jakarta.validation.constraints.*;


public class LoginRequest
{
    @NotEmpty
    private String email;
    @NotBlank
    private String password;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

}
