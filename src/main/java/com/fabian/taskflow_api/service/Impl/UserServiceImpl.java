package com.fabian.taskflow_api.service.Impl;

import com.fabian.taskflow_api.dto.request.RegisterUserRequest;
import com.fabian.taskflow_api.dto.response.RegisterUserResponse;
import com.fabian.taskflow_api.dto.response.UserResponse;
import com.fabian.taskflow_api.entity.Role;
import com.fabian.taskflow_api.entity.User;
import com.fabian.taskflow_api.entity.UserCredential;
import com.fabian.taskflow_api.exception.EmailAlreadyExistsException;
import com.fabian.taskflow_api.repository.RoleRepository;
import com.fabian.taskflow_api.repository.UserCredentialRepository;
import com.fabian.taskflow_api.repository.UserRepository;
import com.fabian.taskflow_api.service.Interfaces.UserService;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserCredentialRepository userCredentialRepository;
    public static final String ROLE_USER = "USER";

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, UserCredentialRepository userCredentialRepository,PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userCredentialRepository = userCredentialRepository;
        this.passwordEncoder = passwordEncoder;

    }

    @Override
    @Transactional
    public RegisterUserResponse register(RegisterUserRequest request)
    {
        ValidateEmail(request.getEmail());
        User user = createUser(request);
        userRepository.save(user);

        createCredentials(user,request.getPassword());

        return buildResponse(user);

    }

    private void ValidateEmail(String email)
    {
        if (userRepository.existsByEmail(email)) {
            throw new EmailAlreadyExistsException(email);
        }
    }

    private Role getDefaultRole() {
        return roleRepository.findByNombreRol(ROLE_USER)
                    .orElseThrow(() -> new IllegalStateException("El rol USER no existe"));
    }

    private User createUser(RegisterUserRequest request)
    {
        User user = new User();
        user.setNombre(request.getNombre());
        user.setApellidoPaterno(request.getApellidoPaterno());
        user.setApellidoMaterno(request.getApellidoMaterno());
        user.setEmail(request.getEmail());
        user.setSexo(request.getSexo());
        user.setFechaNacimiento(request.getFechaNacimiento());
        user.setRol(getDefaultRole());
        user.setActivo(true);

        return user;
    }

    private RegisterUserResponse buildResponse(User user)
    {
        RegisterUserResponse response = new RegisterUserResponse();
        response.setNombreCompleto(user.getNombre() + " " + user.getApellidoPaterno() + " " + user.getApellidoMaterno());
        response.setEmail(user.getEmail());
        response.setFechaRegistro(user.getFechaRegistro());
        response.setId(user.getIdUser());
        response.setNombreRol(user.getRol().getNombreRol());

        return response;
    }

    private void createCredentials(User user, String password)
    {
        String hash = passwordEncoder.encode(password);
        userCredentialRepository.save(new UserCredential(user, hash));// TODO: Hash password before saving
    }

    public List<UserResponse> getAllUsers()
    {
        return userRepository.findAll().stream()
                .map(user -> new UserResponse(
                user.getNombre(),
                user.getApellidoPaterno(),
                user.getApellidoMaterno(),
                user.getEmail())).toList();
    }
}
