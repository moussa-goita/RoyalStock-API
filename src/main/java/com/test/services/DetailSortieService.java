package com.test.services;

import com.test.entities.BonEntree;
import com.test.entities.BonSortie;
import com.test.entities.DetailSortie;
import com.test.entities.Produit;
import com.test.repositories.BonSortieRepository;
import com.test.repositories.DetailSortieRepository;
import com.test.repositories.ProduitRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetailSortieService {

    @Autowired
    private DetailSortieRepository detailSortieRepository;

    @Autowired
    private ProduitRepository produitRepository;
    @Autowired
    private BonSortieRepository bonSortieRepository;

    public List<DetailSortie> findAll() {
        return detailSortieRepository.findAll();
    }

    public Optional<DetailSortie> findById(int id) {
        return detailSortieRepository.findById(id);
    }

    @Transactional
    public DetailSortie save(DetailSortie detailSortie) {
        // Sauvegarder le détail de sortie
        DetailSortie savedDetailSortie = detailSortieRepository.save(detailSortie);

        BonSortie bonSortie = bonSortieRepository.findById(savedDetailSortie.getBonSortie().getId())
                .orElseThrow(() -> new RuntimeException("BonSortie not found with id " + savedDetailSortie.getBonSortie().getId()));

        // Mettre à jour la quantité du produit associé
        int nouvelleQte = savedDetailSortie.getQuantity();
        Produit produit = detailSortie.getProduit();

        if (produit != null) {
            if (nouvelleQte > produit.getQuantity()) {
                throw new RuntimeException("La quantité du BonSortie ne peut pas être supérieure à la quantité disponible du produit.");
            }
                produit.setQuantity(produit.getQuantity() - nouvelleQte);
                System.out.println(produit.getQuantity());
                produitRepository.save(produit);
                bonSortieRepository.save(bonSortie);
            }

            return savedDetailSortie;

    }

    public void deleteById(int id) {
        detailSortieRepository.deleteById(id);
    }
}
