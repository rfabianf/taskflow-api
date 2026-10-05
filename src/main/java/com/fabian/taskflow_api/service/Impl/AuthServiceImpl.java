package com.fabian.taskflow_api.service.Impl;

import com.fabian.taskflow_api.dto.request.LoginRequest;
import com.fabian.taskflow_api.dto.response.AuthResponse;
import com.fabian.taskflow_api.dto.response.CurrentUserResponse;
import com.fabian.taskflow_api.dto.response.LoginResponse;
import com.fabian.taskflow_api.dto.response.LoginResult;
import com.fabian.taskflow_api.entity.RefreshToken;
import com.fabian.taskflow_api.entity.User;
import com.fabian.taskflow_api.repository.UserRepository;
import com.fabian.taskflow_api.security.GoogleTokenService;
import com.fabian.taskflow_api.security.GoogleUserInfo;
import com.fabian.taskflow_api.security.JwtService;
import com.fabian.taskflow_api.service.Interfaces.AuthService;
import com.fabian.taskflow_api.service.Interfaces.RefreshTokenService;
import jakarta.transaction.Transactional;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService
{
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;
	private final GoogleTokenService googleTokenService;
    private final RefreshTokenService refreshTokenService;

    public AuthServiceImpl(GoogleTokenService googleTokenService,AuthenticationManager authenticationManager,JwtService jwtService, UserRepository userRepository, UserDetailsService userDetailsService,  RefreshTokenService refreshTokenService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
		this.googleTokenService = googleTokenService;
        this.refreshTokenService = refreshTokenService;
    }

    @Override
    @Transactional
    public LoginResult login(LoginRequest request)
    {

		// Validación moderna y segura (Java 11+)
		if (request.getGoogleCredential() != null && !request.getGoogleCredential().isBlank()) {
			// Código aquí
			return loginWithGoogle(request.getGoogleCredential());
		}

		return loginLocal(request);
		
    }

    private LoginResult loginLocal(LoginRequest request)
    {
		// El Manager se encarga de:
		// 1. Buscar al usuario (usando tu UserDetailsService)
		// 2. Comparar contraseñas (usando tu PasswordEncoder)
		// 3. Lanzar excepciones si algo falla (BadCredentialsException)
		
		Authentication authentication = authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
		);

		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String accessToken = jwtService.generateToken(userDetails);

        User user = userRepository
                .findByEmail(userDetails.getUsername())
                .orElse(null);

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user);

        return new LoginResult(new LoginResponse(
                true,
                accessToken,
                "Bearer",request.getEmail(),user.getNombre(),user.getApellidoPaterno())
                ,refreshToken.getToken()
        );
    }

    private LoginResult loginWithGoogle(String credential)
    {
		String accessToken = null;
		boolean registered = false;
        RefreshToken refreshToken = new RefreshToken();
		GoogleUserInfo googleUser = googleTokenService.getUserInfoFromToken(credential);

		User user = userRepository
				.findByEmail(googleUser.getEmail())
				.orElse(null);
		
		if(user != null)
		{
			UserDetails userDetails =
                userDetailsService.loadUserByUsername(
                        googleUser.getEmail()
                );

			registered = true;
			accessToken = jwtService.generateToken(userDetails);
            refreshToken = refreshTokenService.createRefreshToken(user);
		}
				

        return new LoginResult(new LoginResponse(
				registered,
                accessToken,
                "Bearer",
				googleUser.getEmail(),
				googleUser.getNombre(),
				googleUser.getApellidoPaterno()),refreshToken.getToken()
        );
    }
    @Override
    public CurrentUserResponse getCurrentUser(String email)
    {
        User user = userRepository
                .findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado :" + email) );

        return new CurrentUserResponse(
                user.getIdUser(),
                user.getEmail(),
                user.getNombre(),
                user.getApellidoPaterno(),
                user.getApellidoMaterno(),
                user.getRol().getNombreRol()
        );
    }

    @Override
    public AuthResponse refreshToken(String token) throws Exception {
        RefreshToken refreshToken = refreshTokenService.findByToken(token)
                .orElseThrow(() -> new Exception("Refresh token is not in database!"));

        refreshTokenService.verifyExpiration(refreshToken);

        User user = refreshToken.getUser();

        UserDetails userDetails =
                userDetailsService.loadUserByUsername(
                        user.getEmail()
                );

        String accessToken = jwtService.generateToken(userDetails);

        return new AuthResponse(accessToken);

    }
}