package com.fabian.taskflow_api.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Service
public class JwtAuthenticationFilter extends OncePerRequestFilter
{
    private final JwtService jwtService;
    private final UserDetailsServiceImpl userDetailsServiceImpl;

    public JwtAuthenticationFilter(JwtService jwtService, UserDetailsServiceImpl userDetailsServiceImpl) {
        this.jwtService = jwtService;
        this.userDetailsServiceImpl = userDetailsServiceImpl;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        // 1. Obtener header "Authorization"
        String authHeader = request.getHeader("Authorization");

        // 2. Si es nulo o no empieza con "Bearer ",
        //    continuar con el siguiente filtro
        if(authHeader == null || !authHeader.startsWith("Bearer "))
        {
            chain.doFilter(request,response);
            return;
        }

        // 3. Extraer el token
        String token = authHeader.substring(7);

        try {
            // 4. Extraer username/email del token
            String username = jwtService.extractUserName(token);

            // 5. Si username existe y no hay autenticación previa
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                {
                    UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(username);

                    //5.b Validar token
                    if (jwtService.isTokenValid(token, userDetails)) {
                        // Crear Authentication
                        UsernamePasswordAuthenticationToken authentication;
                        authentication = new UsernamePasswordAuthenticationToken(userDetails,
                                null,
                                userDetails.getAuthorities());

                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }

            }
        }catch (Exception e) {
            // JWT inválido → no autenticamos al usuario
        }
        // 6. Continuar con la cadena de filtros
        chain.doFilter(request,response);
    }
}
