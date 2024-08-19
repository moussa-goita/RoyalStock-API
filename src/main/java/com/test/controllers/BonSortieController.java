package com.test.controllers;

import com.test.entities.*;
import com.test.services.BonSortieService;
import com.test.services.MotifService;
import com.test.services.ProduitService;
import com.test.services.UtilisateurService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/bon-sorties")
public class BonSortieController {

    @Autowired
    private BonSortieService bonSortieService;

    @Autowired
    private ProduitService produitService;

    @Autowired
    private MotifService motifService;

    @Autowired
    private UtilisateurService utilisateurService;

    @GetMapping("entrepot/{entrepotId}")
    public List<BonSortie> getAllBonEntrees(@PathVariable int entrepotId) {
        return bonSortieService.getBonEntrepotByEntrepot(entrepotId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BonSortie> getBonSortieById(@PathVariable int id) {
        return bonSortieService.findById(id)
                .map(bonSortie -> ResponseEntity.ok().body(bonSortie))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<BonSortie> createBonSortie(@RequestBody BonSortie bonSortie, @RequestParam String email) {
        Utilisateur utilisateur = utilisateurService.findByOneEmail(email);
        if (utilisateur == null) {
            return ResponseEntity.badRequest().body(null);
        }

        bonSortie.setUtilisateur(utilisateur);
        bonSortie.setEntrepot(utilisateur.getEntrepot());
        BonSortie createdBonSortie = bonSortieService.save(bonSortie);
        System.out.println(bonSortie);
        return ResponseEntity.ok(createdBonSortie);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BonSortie> updateBonSortie(@PathVariable int id, @RequestBody BonSortie bonSortie) {
        return bonSortieService.findById(id)
                .map(bonsorties -> {
                    bonsorties.setDateSortie(bonSortie.getDateSortie());
                    bonsorties.setMotif(bonSortie.getMotif());
                    bonsorties.setUtilisateur(bonSortie.getUtilisateur());
                    BonSortie updatedBonSortie = bonSortieService.save(bonsorties);
                    return ResponseEntity.ok().body(updatedBonSortie);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBonSortie(@PathVariable int id) {
        bonSortieService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleExceptions(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/topProduitByMotif")
    public ResponseEntity<Map<String, Map<String, Integer>>> getTopProductsByMotif() {
        Map<String, Map<String, Integer>> topProductsByMotif = bonSortieService.getTopProductsByMotif();
        return ResponseEntity.ok(topProductsByMotif);
    }
}
