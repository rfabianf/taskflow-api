package com.fabian.taskflow_api.dto.request;

public interface AuthenticationRequest {

    String getPassword();
    String getEmail();

    String getGoogleCredential();

}