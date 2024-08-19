package com.test.services;

import com.test.entities.Utilisateur;
import com.test.entities.LoginRequest;
import com.test.entities.LoginResponse;
import com.test.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.UUID;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /*public Utilisateur login(String email, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );
        System.out.println("contenu auth=============="+authentication.getDetails());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return utilisateurRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
    }*/
    public Utilisateur login(String email, String password) {
        try {
            // Authentication attempt
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password)
            );

            // Log authentication details
            System.out.println("Authentication details: " + authentication.getDetails());

            // Store authentication in security context
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Fetch and return user details from the repository
            return utilisateurRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("User not found"));
        } catch (Exception e) {
            // Handle and log exceptions
            System.out.println("Authentication failed: " + e.getMessage());
            throw new RuntimeException("Invalid login credentials");
        }
    }

    public String generateToken(Utilisateur utilisateur) {
        try {
            String tokenData = utilisateur.getEmail() + ":" + utilisateur.getPassword() + ":" + System.currentTimeMillis();
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(tokenData.getBytes());
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error generating token", e);
        }
    }

    public String generateSessionId() {
        return UUID.randomUUID().toString();
    }

}
