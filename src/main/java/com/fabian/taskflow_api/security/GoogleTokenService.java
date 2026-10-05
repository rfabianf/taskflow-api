package com.fabian.taskflow_api.security;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class GoogleTokenService {

    private final GoogleIdTokenVerifier verifier;

    public GoogleTokenService(
            @Value("${google.client-id}") String clientId
    ) {
        this.verifier = new GoogleIdTokenVerifier.Builder(
                new NetHttpTransport(),
                GsonFactory.getDefaultInstance()
        )
                .setAudience(Collections.singletonList(clientId))
                .build();
    }

    public String getEmailFromToken(String credential) {

        try {
            GoogleIdToken idToken = verifier.verify(credential);

            if (idToken == null) {
                throw new RuntimeException("Google ID Token inválido");
            }

            return idToken.getPayload().getEmail();

        } catch (Exception e) {
            throw new RuntimeException("Error validando Google ID Token", e);
        }
    }
	
	public GoogleUserInfo getUserInfoFromToken(String credential) {
    try {
        GoogleIdToken idToken = verifier.verify(credential);

        if (idToken == null) {
            throw new RuntimeException("Google ID Token inválido");
        }

        GoogleIdToken.Payload payload = idToken.getPayload();

        return new GoogleUserInfo(
                payload.getEmail(),
                (String) payload.get("given_name"),
                (String) payload.get("family_name")
        );

    } catch (Exception e) {
        throw new RuntimeException("Error validando Google ID Token", e);
    }
}
}