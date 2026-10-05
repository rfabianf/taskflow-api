package com.fabian.taskflow_api.validation;
import com.fabian.taskflow_api.dto.request.AuthenticationRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AuthenticationValidator
        implements ConstraintValidator<
                        ValidAuthentication,
                        AuthenticationRequest> {

    @Override
    public boolean isValid(
            AuthenticationRequest request,
            ConstraintValidatorContext context) {

        if (request == null) {
            return true;
        }

        boolean hasGoogleCredential =
                request.getGoogleCredential() != null &&
                !request.getGoogleCredential().isBlank();

        if (hasGoogleCredential) {
            return true;
        }

        boolean hasPassword =
                request.getPassword() != null &&
                        !request.getPassword().isBlank();

        boolean hasEmail =
                request.getEmail() != null &&
                        !request.getEmail().isBlank();


        return hasPassword && hasEmail;
    }
}