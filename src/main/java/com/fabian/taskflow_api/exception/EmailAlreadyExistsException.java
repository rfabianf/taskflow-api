package com.fabian.taskflow_api.exception;

public class EmailAlreadyExistsException extends RuntimeException {

    public EmailAlreadyExistsException(String email) {
        super("El correo '" + email + "' ya está registrado.");
    }
}
