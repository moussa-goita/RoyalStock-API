package com.test.controllers;

import com.test.entities.Categorie;
import com.test.entities.Utilisateur;
import com.test.services.CategorieService;
import com.test.services.UtilisateurService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/categories")
public class CategorieController {

    @Autowired
    private CategorieService categorieService;
    @Autowired
    private UtilisateurService utilisateurService;

    @GetMapping
    public List<Categorie> getAllCategories() {
        return categorieService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categorie> getCategorieById(@PathVariable int id) {
        return categorieService.findById(id)
                .map(categorie -> ResponseEntity.ok().body(categorie))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Categorie createCategorie(@RequestBody Categorie categorie) {
        return categorieService.save(categorie);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categorie> updateCategorie(@PathVariable int id, @RequestBody Categorie categorieDetails) {
        return categorieService.findById(id)
                .map(categorie -> {
                    categorie.setName(categorieDetails.getName());
                    Categorie updatedCategorie = categorieService.save(categorie);
                    return ResponseEntity.ok().body(updatedCategorie);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategorie(@PathVariable int id) {
        categorieService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/current")
    public ResponseEntity<List<Categorie>> getCategoriesForCurrentUser(@RequestParam String email) {
        Utilisateur utilisateur = utilisateurService.findByEmail(email).orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        List<Categorie> categories = categorieService.findByEntrepotId(utilisateur.getEntrepot().getId());
        return ResponseEntity.ok(categories);
    }

    @PostMapping("/create")
    public ResponseEntity<Categorie> createCategorie(@RequestBody Map<String, String> categorieData, @RequestParam String email) {
        Utilisateur utilisateur = utilisateurService.findByEmail(email).orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        Categorie categorie = new Categorie();
        categorie.setName(categorieData.get("name"));
        categorie.setCreatedBy(utilisateur);
        categorie.setEntrepot(utilisateur.getEntrepot());
        Categorie savedCategorie = categorieService.save(categorie);
        return ResponseEntity.ok(savedCategorie);
    }

}
