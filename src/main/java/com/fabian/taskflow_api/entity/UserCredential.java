package com.fabian.taskflow_api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.UUID;


@Entity
@Table(name = "user_credentials")
public class UserCredential
{
    public UserCredential() {
    }

    public UserCredential(User user, String password) {
        this.user = user;
        this.password = password;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idUserCredential;
    @OneToOne
    @JoinColumn(name = "id_user", unique = true)
    private User user;
    @NotBlank
    @Size(min = 8, max = 100)
    private String password;
	@Enumerated(EnumType.STRING)
	private AuthProvider authProvider;

    public String getPassword() {
        return password;
    }
}
