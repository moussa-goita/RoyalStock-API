package com.test.services;

import com.test.entities.*;
import com.test.repositories.BonEntreeRepository;
import com.test.repositories.DetailEntreeRepository;
import com.test.repositories.FournisseurRepository;
import com.test.repositories.ProduitRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class BonEntreeService {

    @Autowired
    private BonEntreeRepository bonEntreeRepository;

    @Autowired
    private FournisseurRepository fournisseurRepository;

    @Autowired
    private DetailEntreeRepository detailEntreeRepository;

    @Autowired
    private ProduitRepository produitRepository;
    private UtilisateurService utilisateurService;

    @Autowired
    private NotificationService notificationService;

    // Appel manuel pour tester
    @PostConstruct
    public void init() {
        verifierBonsNonValides();
    }
//    public List<BonEntree> findAll(int entrepotId) {
//        return bonEntreeRepository.findAllByEntrepotId(entrepotId);
//    }

    // S'exécute chaque jour à 00h00
    @Scheduled(cron = "0 0 0 * * ?")
    public void verifierBonsNonValides() {
        List<BonEntree> bonsNonValides = bonEntreeRepository.findAllByStatutAndDateCommandeBefore("En Cours", LocalDate.now().minus(7, ChronoUnit.DAYS));

        for (BonEntree bon : bonsNonValides) {
            notificationService.envoyerNotificationManager(bon);
        }
    }
    public Optional<BonEntree> findById(int id) {
        return bonEntreeRepository.findById(id);
    }

    @Transactional
    public BonEntree save(BonEntree bonEntree) {
        Fournisseur fournisseur = bonEntree.getFournisseur();
        if (fournisseur != null && fournisseur.getId() == 0) {
            fournisseur = fournisseurRepository.save(fournisseur);
            bonEntree.setFournisseur(fournisseur);
        }

        return bonEntreeRepository.save(bonEntree);
    }

    @Transactional
    public BonEntree validerBonEntree(Long bonEntreeId) {
        BonEntree bonEntree = bonEntreeRepository.findById(Math.toIntExact(bonEntreeId))
                .orElseThrow(() -> new RuntimeException("Aucun id pour le bon  " + bonEntreeId));

        // Récupérer les détails d'entrée associés
        List<DetailEntree> detailsEntree = detailEntreeRepository.findByBonEntree(bonEntree);

        // Mettre à jour les quantités des produits
        for (DetailEntree detail : detailsEntree) {
            Produit produit = detail.getProduit();
            int nouvelleQuantite = produit.getQuantity() + detail.getQuantite();
            produit.setQuantity(nouvelleQuantite);
            produitRepository.save(produit);
        }

        // Mettre à jour le bon d'entrée comme validé (ajouter un champ si nécessaire)
        bonEntree.setStatut("En stock");
        return bonEntreeRepository.save(bonEntree);
    }

    public void deleteById(int id) {
        bonEntreeRepository.deleteById(id);
    }

    public List<BonEntree> getBonEntrepotByEntrepot(int entrepotId) {
        return bonEntreeRepository.findAllByEntrepotId(entrepotId);
    }

}
