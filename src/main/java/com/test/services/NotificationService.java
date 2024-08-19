package com.test.services;

import com.test.entities.*;
import com.test.repositories.NotificationRepository;
import com.test.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;@Autowired
    private UtilisateurRepository utilisateurRepository;

    public void envoyerNotificationManager(BonEntree bonEntree) {
        Utilisateur manager = utilisateurRepository.findManagerByEntrepot(bonEntree.getEntrepot().getId())
                    .orElseThrow(() -> new RuntimeException("Manager non trouvé pour l'entrepôt: " + bonEntree.getEntrepot().getId()));
        String message = "Le bon de entre #00" + bonEntree.getId() + " n'a pas été validé depuis une semaine.";

        boolean notificationExists = notificationRepository.existsByMessageAndUtilisateurAndType(message, manager, "ALERTE");

        if (!notificationExists) {
            Notification notification = new Notification();
            notification.setMessage(message);
            notification.setType("ALERTE");
            notification.setUtilisateur(manager);
            notification.setEntrepot(bonEntree.getEntrepot());
            notification.setCreateDay(new Date());

            notificationRepository.save(notification);
            }
    }

    public void notificationSeuil(Produit produit) {
        if (produit.getQuantity() <= produit.getSeuil()) {
            boolean notificationExists = notificationRepository.existsByProduitAndType(produit, "STOCK_ALERT");

            if (!notificationExists) {
                Notification notification = new Notification();
                notification.setMessage("Le produit " + produit.getProductName() + " a atteint le seuil.");
                notification.setType("STOCK_ALERT");
                notification.setEntrepot(produit.getEntrepot());

                // Ajouter une notification pour chaque utilisateur (vendeur) associé à l'entrepôt
                List<Utilisateur> utilisateurs = utilisateurRepository.findAllByEntrepot(produit.getEntrepot());

                for (Utilisateur utilisateur : utilisateurs) {
                    Notification utilisateurNotification = new Notification();
                    utilisateurNotification.setMessage(notification.getMessage());
                    utilisateurNotification.setType(notification.getType());
                    utilisateurNotification.setEntrepot(notification.getEntrepot());
                    utilisateurNotification.setUtilisateur(utilisateur);
                    notificationRepository.save(utilisateurNotification);
                }
            }
        }
    }

    public void envoyerNotificationExpiration(Produit produit) {
        boolean notificationExists = notificationRepository.existsByProduitAndType(produit, "EXPIRATION");

        if (!notificationExists) {
            Notification notification = new Notification();
            notification.setMessage("Le produit " + produit.getProductName() + " expire dans deux semaines.");
            notification.setProduit(produit);
            notification.setType("EXPIRATION");
            notification.setEntrepot(produit.getEntrepot());
            notification.setUtilisateur(produit.getCreatedBy());
            notificationRepository.save(notification);
        }
    }

    public List<Notification> getNotificationsByUtilisateur(Integer utilisateurId) {
        return notificationRepository.findByUtilisateurId(utilisateurId);
    }
    public long countNotificationsByType(String type, Entrepot entrepot) {
        return notificationRepository.countByTypeAndEntrepot(type, entrepot);
    }

    public List<Notification> filterNotificationsByType(String type, Entrepot entrepot) {
        return notificationRepository.findByTypeAndEntrepot(type, entrepot);
    }

    public List<Notification> getNotificationsByEntrepot(Integer entrepotId) {
        return notificationRepository.findByEntrepotId(entrepotId);
    }

    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

}
