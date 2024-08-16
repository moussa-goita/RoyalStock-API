package com.test.repositories;

import com.test.entities.Entrepot;
import com.test.entities.Notification;
import com.test.entities.Produit;
import com.test.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {
    List<Notification> findByUtilisateurId(Integer utilisateurId);
    List<Notification> findByEntrepotId(Integer entrepotId);
    long countByType(String type);
    List<Notification> findByType(String type);
    boolean existsByProduitAndType(Produit produit, String type);
    boolean existsByMessageAndUtilisateurAndType(String message, Utilisateur manager, String type);
    long countByTypeAndEntrepot(String type, Entrepot entrepot);
    List<Notification> findByTypeAndEntrepot(String type, Entrepot entrepot);
}
