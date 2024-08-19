package com.test.services;

import com.test.entities.Role;
import com.test.entities.Utilisateur;
import com.test.repositories.RoleRepository;
import com.test.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DataLoader implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Override
    public void run(String... args) throws Exception {
    // Créer les rôles par défaut
    createRoleIfNotFound("VENDEUR");
    createRoleIfNotFound("MANAGER");
    createRoleIfNotFound("ADMIN");

    // Créer les utilisateurs par défaut
    createUserIfNotFound("admin", "admin@example.com", "password", "ADMIN");
}

private void createRoleIfNotFound(String roleName) {
    Optional<Role> role = roleRepository.findByName(roleName);
    if (role.isEmpty()) {
        Role newRole = new Role();
        newRole.setName(roleName);
        roleRepository.save(newRole);
    }
}

private void createUserIfNotFound(String username, String email, String password, String roleName) {
    Optional<Utilisateur> utilisateur = utilisateurRepository.findByUsername(username);
    if (utilisateur.isEmpty()) {
        Utilisateur newUser = new Utilisateur();
        newUser.setUsername(username);
        newUser.setEmail(email);
        newUser.setPassword(new BCryptPasswordEncoder().encode(password));
        Role role = roleRepository.findByName(roleName).orElse(null);
        newUser.setRole(role);
        utilisateurRepository.save(newUser);
    }
}
}
