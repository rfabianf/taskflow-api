package com.fabian.taskflow_api.service.Impl;

import com.fabian.taskflow_api.dto.request.LoginRequest;
import com.fabian.taskflow_api.dto.request.RegisterUserRequest;
import com.fabian.taskflow_api.dto.response.LoginResponse;
import com.fabian.taskflow_api.dto.response.LoginResult;
import com.fabian.taskflow_api.dto.response.RegisterUserResponse;
import com.fabian.taskflow_api.dto.response.UserResponse;
import com.fabian.taskflow_api.entity.Role;
import com.fabian.taskflow_api.entity.User;
import com.fabian.taskflow_api.entity.UserCredential;
import com.fabian.taskflow_api.exception.EmailAlreadyExistsException;
import com.fabian.taskflow_api.repository.RoleRepository;
import com.fabian.taskflow_api.repository.UserCredentialRepository;
import com.fabian.taskflow_api.repository.UserRepository;
import com.fabian.taskflow_api.service.Interfaces.AuthService;
import com.fabian.taskflow_api.service.Interfaces.RegistrationService;
import com.fabian.taskflow_api.service.Interfaces.UserService;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class RegistrationServiceImpl implements RegistrationService {
    private final AuthService authService;
    private final UserService userService;

    public RegistrationServiceImpl(AuthService authService ,UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @Override
    @Transactional
    public LoginResult registerGoogle(RegisterUserRequest request)
    {
        RegisterUserResponse resp = userService.register(request);


        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setGoogleCredential(request.getGoogleCredential());

        return authService.login(loginRequest);

    }
}
