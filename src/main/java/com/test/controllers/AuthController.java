package com.test.controllers;

import com.test.services.AuthService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.test.entities.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/utilisateurs")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> loginRequest) {
        String email = loginRequest.get("email");
        String password = loginRequest.get("password");

        Utilisateur utilisateur = authService.login(email, password);
        String token = authService.generateToken(utilisateur);
        String sessionId = authService.generateSessionId();

        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("username", utilisateur.getUsername());
        response.put("email", utilisateur.getEmail());
        response.put("role", utilisateur.getRole().getName());
        response.put("session", sessionId);
        response.put("id", utilisateur.getId());

        if (utilisateur.getEntrepot() != null) {
            Map<String, Object> entrepotInfo = new HashMap<>();
            entrepotInfo.put("entrepotId", utilisateur.getEntrepot().getId());
            entrepotInfo.put("entrepotName", utilisateur.getEntrepot().getEntrepotName());
            response.put("entrepot", entrepotInfo);
        }
        return ResponseEntity.ok(response);
    }
}
