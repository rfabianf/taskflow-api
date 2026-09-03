package com.fabian.taskflow_api.service.Interfaces;

import com.fabian.taskflow_api.dto.request.LoginRequest;
import com.fabian.taskflow_api.dto.request.RegisterUserRequest;
import com.fabian.taskflow_api.dto.response.LoginResponse;

public interface AuthService {
    LoginResponse Login(LoginRequest request);
}
