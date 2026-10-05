package com.fabian.taskflow_api.service.Interfaces;

import com.fabian.taskflow_api.dto.request.LoginRequest;
import com.fabian.taskflow_api.dto.request.RegisterUserRequest;
import com.fabian.taskflow_api.dto.response.AuthResponse;
import com.fabian.taskflow_api.dto.response.CurrentUserResponse;
import com.fabian.taskflow_api.dto.response.LoginResponse;
import com.fabian.taskflow_api.dto.response.LoginResult;

public interface AuthService {
    LoginResult login(LoginRequest request);
    CurrentUserResponse getCurrentUser(String email);
    AuthResponse refreshToken(String token) throws Exception;
}
