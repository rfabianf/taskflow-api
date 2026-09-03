package com.fabian.taskflow_api.controller;

import com.fabian.taskflow_api.dto.request.LoginRequest;
import com.fabian.taskflow_api.dto.request.RegisterUserRequest;
import com.fabian.taskflow_api.dto.response.LoginResponse;
import com.fabian.taskflow_api.dto.response.RegisterUserResponse;
import com.fabian.taskflow_api.service.Interfaces.AuthService;
import com.fabian.taskflow_api.service.Interfaces.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
//@RequiredArgsConstructor
//@Operation(summary = "Registrar usuario")
public class AuthController
{
    private final AuthService authService;
    private final UserService userService;

    public AuthController(AuthService authService ,UserService userService)
    {
        this.userService = userService;
        this.authService = authService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
   public RegisterUserResponse registerUser(@Valid @RequestBody RegisterUserRequest registerUserRequest)
   {
       return userService.register(registerUserRequest);
   }

   @PostMapping("/login")
   public LoginResponse login(@Valid @RequestBody LoginRequest request)
   {
       System.out.println("ENTRO AL LOGIN");
       return authService.Login(request);
   }
}
