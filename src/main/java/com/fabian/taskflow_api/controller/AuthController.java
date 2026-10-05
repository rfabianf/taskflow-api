package com.fabian.taskflow_api.controller;

import com.fabian.taskflow_api.dto.request.LoginRequest;
import com.fabian.taskflow_api.dto.request.RegisterUserRequest;
import com.fabian.taskflow_api.dto.response.*;
import com.fabian.taskflow_api.service.Interfaces.AuthService;
import com.fabian.taskflow_api.service.Interfaces.RefreshTokenService;
import com.fabian.taskflow_api.service.Interfaces.RegistrationService;
import com.fabian.taskflow_api.service.Interfaces.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.time.Duration;
import java.util.List;

@RestController
@RequestMapping("/api/auth")
//@RequiredArgsConstructor
//@Operation(summary = "Registrar usuario")
public class AuthController
{
    private final AuthService authService;
    private final UserService userService;
    private final RegistrationService registrationService;
    private final RefreshTokenService refreshTokenService;

    public AuthController(AuthService authService ,UserService userService,RegistrationService registrationService,RefreshTokenService refreshTokenService)
    {
        this.userService = userService;
        this.authService = authService;
        this.registrationService = registrationService;
        this.refreshTokenService = refreshTokenService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
   public RegisterUserResponse registerUser(@Valid @RequestBody RegisterUserRequest registerUserRequest)
   {
       return userService.register(registerUserRequest);
   }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/google")
    public LoginResponse registerGoogle(@Valid @RequestBody RegisterUserRequest registerUserRequest,
                                      HttpServletResponse response)
    {
        LoginResult result = registrationService.registerGoogle(registerUserRequest);
        ResponseCookie cookie = ResponseCookie.from(
                        "refreshToken",result.refreshToken()
                )
                .httpOnly(true)
                .secure(false)
                .sameSite("Lax")
                .path("/api/auth")
                .maxAge(Duration.ofDays(1))
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        return result.loginResponse();

    }

   @PostMapping("/login")
   public LoginResponse login(@Valid @RequestBody LoginRequest request,
                            HttpServletResponse response)
   {
       LoginResult result = authService.login(request);
       ResponseCookie cookie = ResponseCookie.from(
               "refreshToken",result.refreshToken()
       )
           .httpOnly(true)
           .secure(false)
           .sameSite("Lax")
           .path("/api/auth")
           .maxAge(Duration.ofDays(1))
           .build();

       response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

       return result.loginResponse();

   }

    @GetMapping("/me")
    public CurrentUserResponse getCurrentUser(Authentication authentication)
    {
        return authService.getCurrentUser(authentication.getName());
    }

    @PostMapping("/refresh")
    public AuthResponse refreshToken(
            @CookieValue(name = "refreshToken") String refreshToken) throws Exception {
        return authService.refreshToken(refreshToken);
    }

    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void logout(@CookieValue(name = "refreshToken", required = false) String refreshToken,
                               HttpServletResponse response) throws Exception {

        if(refreshToken != null)
        {
            refreshTokenService.deleteByToken(refreshToken);
        }

        ResponseCookie cookie = ResponseCookie.from("refreshToken","")
                .httpOnly(true)
                .secure(false)
                .sameSite("Lax")
                .path("/api/auth")
                .maxAge(0)
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
    }
}
