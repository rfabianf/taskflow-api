package com.fabian.taskflow_api.service.Interfaces;

import com.fabian.taskflow_api.dto.request.RegisterUserRequest;
import com.fabian.taskflow_api.dto.response.LoginResponse;
import com.fabian.taskflow_api.dto.response.LoginResult;
import com.fabian.taskflow_api.dto.response.RegisterUserResponse;
import com.fabian.taskflow_api.dto.response.UserResponse;

import java.util.List;

public interface RegistrationService {
    LoginResult registerGoogle(RegisterUserRequest request);
}
