package com.test.controllers;

import com.test.entities.Fournisseur;
import com.test.entities.Produit;
import com.test.entities.Utilisateur;
import com.test.services.FournisseurService;
import com.test.services.UtilisateurService;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fournisseurs")
public class FournisseurController {

    @Autowired
    private FournisseurService fournisseurService;
    @Autowired
    private UtilisateurService utilisateurService;

    @GetMapping("/count")
    public int getFournisseursCount() {
        return fournisseurService.getFournisseursCount();
    }

    @GetMapping
    public List<Fournisseur> getAllFournisseurs() {
        return fournisseurService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fournisseur> getFournisseurById(@PathVariable int id) {
        return fournisseurService.findById(id)
                .map(fournisseur -> ResponseEntity.ok().body(fournisseur))
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping("/create")
    public ResponseEntity<Fournisseur> createFournisseur(@RequestBody Fournisseur fournisseur, @RequestParam String email) {
        Utilisateur utilisateur = utilisateurService.findByOneEmail(email);
        if (utilisateur == null) {
            return ResponseEntity.badRequest().body(null);
        }
        fournisseur.setCreatedBy(utilisateur);
        fournisseur.setEntrepot(utilisateur.getEntrepot());

        Fournisseur savedFournisseur = fournisseurService.save(fournisseur);
        return ResponseEntity.ok(savedFournisseur);
    }

    @GetMapping("/current")
    public ResponseEntity<List<Fournisseur>> getCategoriesForCurrentUser(@RequestParam String email) {
        Utilisateur utilisateur = utilisateurService.findByEmail(email).orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        List<Fournisseur> fournisseurs = fournisseurService.findByEntrepotId(utilisateur.getEntrepot().getId());
        return ResponseEntity.ok(fournisseurs);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Fournisseur> updateFournisseur(@PathVariable int id, @RequestBody Fournisseur fournisseurDetails) {
        return fournisseurService.findById(id)
                .map(fournisseur -> {
                    fournisseur.setFournName(fournisseurDetails.getFournName());
                    fournisseur.setAdresse(fournisseurDetails.getAdresse());
                    fournisseur.setTelephone(fournisseurDetails.getTelephone());
                    Fournisseur updatedFournisseur = fournisseurService.save(fournisseur);
                    return ResponseEntity.ok().body(updatedFournisseur);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFournisseur(@PathVariable int id) {
        fournisseurService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    //

    @PutMapping("/{id}/rendre-public")
    public ResponseEntity<Fournisseur> rendreFournisseurPublic(@PathVariable Long id) {
        Fournisseur fournisseurMisAJour = fournisseurService.mettreFournisseurPublic(id);
        return ResponseEntity.ok(fournisseurMisAJour);
    }
    // Méthode pour récupérer les fournisseurs publics
    @GetMapping("public")
    public List<Fournisseur> getFournisseursPublic() {
        return fournisseurService.fourPublic();
    }

    //

    @PostMapping("/{id}/noter")
    public ResponseEntity<Fournisseur> noterFournisseur(@PathVariable int id, @RequestParam double note) {
        Fournisseur fournisseurMisAJour = fournisseurService.ajouterNotation(id, note);
        return ResponseEntity.ok(fournisseurMisAJour);
    }

    @GetMapping("/top-rated")
    public ResponseEntity<List<Fournisseur>> getTopRatedFournisseurs() {
        List<Fournisseur> fournisseurs = fournisseurService.findAll();
        fournisseurs.sort((f1, f2) -> Double.compare(f2.getNoteMoyenne(), f1.getNoteMoyenne()));
        List<Fournisseur> topRatedFournisseurs = fournisseurs.stream().limit(10).toList();
        return ResponseEntity.ok(topRatedFournisseurs);
    }

}
