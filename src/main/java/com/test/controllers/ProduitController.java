package com.test.controllers;

import com.test.dto.TopEntreeDTO;
import com.test.dto.TopVenduDTO;
import com.test.entities.Produit;
import com.test.entities.Utilisateur;
import com.test.services.ProduitService;
import com.test.services.UtilisateurService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produits")
public class ProduitController {

    @Autowired
    private ProduitService produitService;
    @Autowired
    private UtilisateurService utilisateurService;

    @GetMapping
    public List<Produit> getAllProduits() {
        return produitService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produit> getProduitById(@PathVariable int id) {
        return produitService.findById(id)
                .map(produit -> ResponseEntity.ok().body(produit))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Produit> createProduit(@RequestBody Produit produit) {
        try {
            if (produit.getCategorie() == null || produit.getCategorie().getId() == 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            Produit savedProduit = produitService.save(produit);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedProduit);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produit> updateProduit(@PathVariable int id, @RequestBody Produit produitDetails) {
        return produitService.findById(id)
                .map(produit -> {
                    produit.setProductName(produitDetails.getProductName());
                    produit.setDescription(produitDetails.getDescription());
                    produit.setCreatedBy(produitDetails.getCreatedBy());
                    produit.setQuantity(produitDetails.getQuantity());
                    //produit.setSeuil(produitDetails.getSeuil());
                    produit.setCategorie(produitDetails.getCategorie());
                    Produit updatedProduit = produitService.save(produit);
                    return ResponseEntity.ok().body(updatedProduit);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduit(@PathVariable int id) {
        produitService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/create")
    public ResponseEntity<Produit> createProduit(@RequestBody Produit produit, @RequestParam String email) {
        Utilisateur utilisateur = utilisateurService.findByOneEmail(email);
        if (utilisateur == null) {
            return ResponseEntity.badRequest().body(null);
        }

        produit.setCreatedBy(utilisateur);
        produit.setEntrepot(utilisateur.getEntrepot());

        Produit savedProduit = produitService.save(produit);
        return ResponseEntity.ok(savedProduit);
    }

    @GetMapping("/entrepot/{entrepotId}")
    public ResponseEntity<List<Produit>> getProduitsByEntrepot(@PathVariable int entrepotId) {
        List<Produit> produits = produitService.findByEntrepotId(entrepotId);
        return ResponseEntity.ok(produits);
    }
}
