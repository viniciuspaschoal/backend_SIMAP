package com.simap.simap_backend.service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import org.springframework.stereotype.Service;

@Service
public class FirebaseAuthService {

    public FirebaseToken validateToken(String idToken) {
        try {

            return FirebaseAuth.getInstance().verifyIdToken(idToken);
        } catch (Exception e){

            throw new RuntimeException("Token inválido ou expirado");
        }
    }
}
