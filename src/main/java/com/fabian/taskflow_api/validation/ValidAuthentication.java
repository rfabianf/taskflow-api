package com.fabian.taskflow_api.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AuthenticationValidator.class)
public @interface ValidAuthentication {

    String message() default "Debe proporcionar password o googleCredential, pero no ambos";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}