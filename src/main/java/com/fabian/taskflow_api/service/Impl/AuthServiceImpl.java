package com.fabian.taskflow_api.service.Impl;

import com.fabian.taskflow_api.dto.request.LoginRequest;
import com.fabian.taskflow_api.dto.response.LoginResponse;
import com.fabian.taskflow_api.entity.User;
import com.fabian.taskflow_api.repository.UserRepository;
import com.fabian.taskflow_api.security.JwtService;
import com.fabian.taskflow_api.security.UserDetailsServiceImpl;
import com.fabian.taskflow_api.service.Interfaces.AuthService;
import jakarta.transaction.Transactional;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService
{
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final UserDetailsServiceImpl userDetailsServiceImpl;
    private final JwtService jwtService;

    public AuthServiceImpl(AuthenticationManager authenticationManager,JwtService jwtService, UserRepository userRepository, UserDetailsServiceImpl userDetailsServiceImply) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.userDetailsServiceImpl = userDetailsServiceImply;
    }

    @Override
    @Transactional
    public LoginResponse Login(LoginRequest request)
    {
        // El Manager se encarga de:
        // 1. Buscar al usuario (usando tu UserDetailsServiceImpl)
        // 2. Comparar contraseñas (usando tu PasswordEncoder)
        // 3. Lanzar excepciones si algo falla (BadCredentialsException)
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );


        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String accessToken = jwtService.generateToken(userDetails);



        return new LoginResponse(
                accessToken,
                "Bearer"
        );
    }
}
