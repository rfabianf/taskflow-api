package com.fabian.taskflow_api.security;

public class GoogleUserInfo {

    private String email;
    private String nombre;
    private String apellidoPaterno;

    public GoogleUserInfo(
            String email,
            String nombre,
            String apellidoPaterno) {

        this.email = email;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getEmail() {
        return email;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }
}