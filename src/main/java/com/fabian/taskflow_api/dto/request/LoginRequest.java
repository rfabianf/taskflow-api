package com.fabian.taskflow_api.dto.request;

import com.fabian.taskflow_api.validation.ValidAuthentication;
import jakarta.validation.constraints.*;

@ValidAuthentication
public class LoginRequest implements AuthenticationRequest
{
    private String email;
    private String password;
    private String googleCredential;

    public void setGoogleCredential(String googleCredential) {
        this.googleCredential = googleCredential;
    }

    public String getEmail() {
        return email;
    }

	@Override
    public String getPassword() {
        return password;
    }
	
	@Override
	public String getGoogleCredential() {
        return googleCredential;
    }
}
