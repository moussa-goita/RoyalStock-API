package com.test.controllers;

import com.test.entities.Entrepot;
import com.test.entities.Notification;
import com.test.entities.Utilisateur;
import com.test.repositories.EntrepotRepository;
import com.test.repositories.UtilisateurRepository;
import com.test.services.EntrepotService;
import com.test.services.NotificationService;
import com.test.services.UtilisateurService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;
    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    private EntrepotRepository entrepotRepository;

    @GetMapping("/utilisateur/{utilisateurId}")
    public ResponseEntity<List<Notification>> getNotificationsByUtilisateur(@PathVariable Integer utilisateurId) {
        List<Notification> notifications = notificationService.getNotificationsByUtilisateur(utilisateurId);
        return ResponseEntity.ok(notifications);
    }
    @GetMapping("/entrepot/{entrepotId}")
    public ResponseEntity<List<Notification>> getNotificationsByEntrepot(@PathVariable Integer entrepotId) {
        List<Notification> notifications = notificationService.getNotificationsByEntrepot(entrepotId);
        return ResponseEntity.ok(notifications);
    }
    @GetMapping
    public ResponseEntity<List<Notification>> getAllNotifications() {
        List<Notification> notifications = notificationService.getAllNotifications();
        return ResponseEntity.ok(notifications);
    }

    @GetMapping("/count")
    public long countNotificationsByType(
            @RequestParam String type,
            @RequestParam Integer entrepotId) {

        Entrepot entrepot = entrepotRepository.findById(entrepotId)
            .orElseThrow(() -> new RuntimeException("Entrepôt non trouvé"));

        return notificationService.countNotificationsByType(type, entrepot);
    }
    @GetMapping("/filter")
    public List<Notification> filterNotificationsByType(
            @RequestParam String type,
            @RequestParam Integer entrepotId) {

        Entrepot entrepot = entrepotRepository.findById(entrepotId)
                .orElseThrow(() -> new RuntimeException("Entrepôt non trouvé"));

        return notificationService.filterNotificationsByType(type, entrepot);
    }

}
