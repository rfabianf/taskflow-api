package com.fabian.taskflow_api.security;

import com.fabian.taskflow_api.entity.User;
import com.fabian.taskflow_api.entity.UserCredential;
import com.fabian.taskflow_api.repository.UserCredentialRepository;
import com.fabian.taskflow_api.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService
{
    private final UserRepository userRepository;
    private final UserCredentialRepository userCredentialRepository;

    public UserDetailsServiceImpl(UserRepository userRepository,UserCredentialRepository userCredentialRepository)
    {
        this.userRepository = userRepository;
        this.userCredentialRepository = userCredentialRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException
    {
        //1. Buscamos el usuario por su email
        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->  new UsernameNotFoundException("Usuario no encontrado con email:" + email));

        UserCredential credential = userCredentialRepository.findByUser(user)
                .orElse(null);

        String password = credential != null
                ? credential.getPassword()
                : "{noop}GOOGLE_USER";

        SimpleGrantedAuthority authority =
                new SimpleGrantedAuthority(user.getRol().getNombreRol());

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                password,
                Collections.singletonList(authority)
        );
    }
}
