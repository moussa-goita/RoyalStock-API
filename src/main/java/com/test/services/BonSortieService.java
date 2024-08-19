package com.test.services;

import com.test.entities.*;
import com.test.repositories.BonSortieRepository;
import com.test.repositories.DetailSortieRepository;
import com.test.repositories.MotifRepository;
import com.test.repositories.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BonSortieService {

    @Autowired
    private BonSortieRepository bonSortieRepository;

    @Autowired
    private ProduitRepository produitRepository;

    @Autowired
    private DetailSortieRepository detailSortieRepository;

    @Autowired
    private MotifRepository motifRepository;

    @Autowired
    private NotificationService notificationService;

    public List<BonSortie> getBonEntrepotByEntrepot(int entrepotId) {
        return bonSortieRepository.findAllByEntrepotId(entrepotId);
    }


    public Optional<BonSortie> findById(int id) {
        return bonSortieRepository.findById(id);
    }

    public BonSortie save(BonSortie bonSortie) {
        Motif motif = bonSortie.getMotif();
        if (motif != null && motif.getId() == 0) {
            motif = motifRepository.save(motif);
            bonSortie.setMotif(motif);
        }
        return bonSortieRepository.save(bonSortie);
    }

    public void deleteById(int id) {
        bonSortieRepository.deleteById(id);
    }




    //Produit la plus sortie en fonction du motif
    public Map<String, Map<String, Integer>> getTopProductsByMotif() {
        List<BonSortie> bonSorties = bonSortieRepository.findAll();
        Map<String, Map<String, Integer>> topProductsByMotif = new HashMap<>();

        for (BonSortie bonSortie : bonSorties) {
            Motif motif = bonSortie.getMotif();
            if (motif == null) {
                continue; // ignore les bons de sortie sans motif
            }
            String motifTitle = motif.getTitle();
            Map<String, Integer> productCountMap = topProductsByMotif.getOrDefault(motifTitle, new HashMap<>());

            for (DetailSortie detailsSortie : bonSortie.getDetailsSorties()) {
                Produit produit = detailsSortie.getProduit();
                if (produit != null) {
                    String productName = String.valueOf(produit.getQuantity());
                    productCountMap.put(productName, Integer.valueOf(productCountMap.getOrDefault(productName, 0) + detailsSortie.getQuantity()));
                }
            }

            topProductsByMotif.put(motifTitle, productCountMap);
        }

        return topProductsByMotif;
    }
}
