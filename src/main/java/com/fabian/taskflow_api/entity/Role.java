package com.fabian.taskflow_api.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "roles")
public class Role {
    public Role() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_rol")
    private UUID id;
    private String nombreRol;

    public String getNombreRol() {
        return nombreRol;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", nombreRol='" + nombreRol + '\'' +
                '}';
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }
}
