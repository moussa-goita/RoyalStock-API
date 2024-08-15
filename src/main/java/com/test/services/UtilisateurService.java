package com.test.services;

import com.test.entities.Entrepot;
import com.test.entities.Role;
import com.test.entities.Utilisateur;
import com.test.repositories.EntrepotRepository;
import com.test.repositories.RoleRepository;
import com.test.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurService implements UserDetailsService  {

    private final UtilisateurRepository utilisateurRepository;
    private final RoleRepository roleRepository;
    private final EntrepotRepository entrepotRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UtilisateurService(UtilisateurRepository utilisateurRepository, RoleRepository roleRepository, EntrepotRepository entrepotRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.utilisateurRepository = utilisateurRepository;
        this.roleRepository = roleRepository;
        this.entrepotRepository = entrepotRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    public Utilisateur createAdmin(String username, String contact, String email, String password) {
        return createUser(username, contact, email, password, "ADMIN", null);
    }

    public Utilisateur createManager(String username, String contact, String email, String password, Entrepot entrepot) {
        validateEmail(email);
        checkUserAlreadyAssignedToEntrepot(email);

        Integer existingManagers = utilisateurRepository.countByRoleAndEntrepotId("MANAGER", entrepot.getId());
        if (existingManagers > 0) {
            throw new RuntimeException("Cet entrepôt a déjà un manager.");
        }

        return createUser(username,contact, email, password,"MANAGER", entrepot);
    }

    public Utilisateur createVendeur(String username, String contact, String email, String password, Integer managerId) {
        validateEmail(email);
        checkUserAlreadyAssignedToEntrepot(email);

        Utilisateur manager = utilisateurRepository.findById(managerId)
                .orElseThrow(() -> new RuntimeException("Manager non trouvé"));

        if (!"MANAGER".equals(manager.getRole().getName())) {
            throw new RuntimeException("Seul un manager peut créer un vendeur.");
        }

        Entrepot entrepot = manager.getEntrepot();
        int vendeurCount = utilisateurRepository.countByRoleAndEntrepotId("VENDEUR", entrepot.getId());

        if (vendeurCount >= 2) {
            throw new RuntimeException("Sorry votre entrepôt ne peu pas avoir plus de deux Vendeur 😎.");
        }
        return createUser(username, contact, email, password, "VENDEUR", entrepot);
    }

    private Utilisateur createUser(String username, String contact, String email, String password, String roleName, Entrepot entrepot) {
        validateEmail(email);
        checkUserAlreadyAssignedToEntrepot(email);

        Optional<Utilisateur> existingUser = utilisateurRepository.findByEmail(email);
        if (existingUser.isPresent()) {
            throw new RuntimeException("Cet utilisateur existe déjà.");
        }

        Utilisateur newUser = new Utilisateur();
        newUser.setUsername(username);
        newUser.setContact(contact);
        newUser.setEmail(email);
        newUser.setPassword(bCryptPasswordEncoder.encode(password));

        Role role = roleRepository.findByName(roleName).orElseGet(() -> {
            Role newRole = new Role();
            newRole.setName(roleName);
            return roleRepository.save(newRole);
        });
        newUser.setRole(role);

        if (entrepot != null) {
            newUser.setEntrepot(entrepot);
        }

        return utilisateurRepository.save(newUser);
    }

    private void validateEmail(String email) {
        if (email == null || !email.contains("@") || !email.contains(".")) {
            throw new RuntimeException("Votre email est invalide");
        }
    }

    private void checkUserAlreadyAssignedToEntrepot(String email) {
        Optional<Utilisateur> existingUser = utilisateurRepository.findByEmail(email);
        if (existingUser.isPresent() && existingUser.get().getEntrepot() != null) {
            throw new RuntimeException("Cet utilisateur est déjà affecté à un entrepôt.");
        }
    }

    public List<Utilisateur> findAll() {
        return utilisateurRepository.findAll();
    }

    public Optional<Utilisateur> findById(int id) {
        return utilisateurRepository.findById(id);
    }

    public void deleteById(int id) {
        utilisateurRepository.deleteById(id);
    }

    public List<Utilisateur> getUtilisateursByUserOrEntrepot(Utilisateur loggedInUser) {
        if ("ADMIN".equals(loggedInUser.getRole().getName())) {
            return utilisateurRepository.findAll();
        } else {
            return utilisateurRepository.findByEntrepotId(loggedInUser.getEntrepot().getId());
        }
    }

    public List<Utilisateur> findByEntrepot(int entrepotId) {
        return utilisateurRepository.findByEntrepotId(entrepotId);
    }

    public Utilisateur update(Utilisateur utilisateur) {
        Optional<Utilisateur> existingUser = utilisateurRepository.findById(utilisateur.getId());
        if (existingUser.isPresent()) {
            Utilisateur userToUpdate = existingUser.get();
            userToUpdate.setUsername(utilisateur.getUsername());
            userToUpdate.setContact(utilisateur.getContact());
            userToUpdate.setEmail(utilisateur.getEmail());
            userToUpdate.setRole(utilisateur.getRole());
            userToUpdate.setEntrepot(utilisateur.getEntrepot());
            if (!utilisateur.getPassword().equals(userToUpdate.getPassword())) {
                userToUpdate.setPassword(bCryptPasswordEncoder.encode(utilisateur.getPassword()));
            }
            return utilisateurRepository.save(userToUpdate);
        } else {
            return null;
        }
    }

    public Optional<Entrepot> findEntrepotById(int entrepotId) {
        return entrepotRepository.findById(entrepotId);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return utilisateurRepository
                .findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur indisponible"));
    }

    public Optional<Utilisateur> findByEmail(String email) {
        return utilisateurRepository.findByEmail(email);
    }

    public Utilisateur findByOneEmail(String email) {
        return utilisateurRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé avec l'email: " + email));
    }
}
