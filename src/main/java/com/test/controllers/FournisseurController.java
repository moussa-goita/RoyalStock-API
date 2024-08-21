package com.test.controllers;

import com.test.entities.Fournisseur;
import com.test.entities.Produit;
import com.test.entities.Statut;
import com.test.entities.Utilisateur;
import com.test.services.FournisseurService;
import com.test.services.UtilisateurService;
import jakarta.persistence.EntityNotFoundException;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Data
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
                    fournisseur.setEmail(fournisseurDetails.getEmail());
                    fournisseur.setStatut(fournisseurDetails.getStatut());
                    fournisseur.setCommentaire(fournisseurDetails.getCommentaire());
                    fournisseur.setNombreNotes(fournisseurDetails.getNombreNotes());
                    fournisseur.setNoteMoyenne(fournisseurDetails.getNoteMoyenne());
                    fournisseur.setService(fournisseurDetails.getService());
                    Fournisseur updatedFournisseur = fournisseurService.save(fournisseur);
                    return ResponseEntity.ok().body(updatedFournisseur);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFournisseur(@PathVariable int id) {
        fournisseurService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/modifier-statut")
    public ResponseEntity<Fournisseur> modifierStatutFournisseur(@PathVariable int id, @RequestBody String newStatut) {
        Fournisseur fournisseur = fournisseurService.findById(id)
                .orElseThrow(() -> new RuntimeException("Fournisseur non trouvé"));

        if ("PUBLIC".equals(newStatut)) {
            fournisseur.setStatut(Statut.PUBLIC);
        } else if ("PRIVE".equals(newStatut)) {
            fournisseur.setStatut(Statut.PRIVE);
        }

        Fournisseur fournisseurMisAJour = fournisseurService.save(fournisseur);
        return ResponseEntity.ok(fournisseurMisAJour);
    }

    // Méthode pour récupérer les fournisseurs publics
    @GetMapping("public")
    public List<Fournisseur> getFournisseursPublic() {
        return fournisseurService.fourPublic();
    }

    @PostMapping("/{id}/noter")
    public ResponseEntity<Fournisseur> noterFournisseur(
            @PathVariable int id,
            @RequestBody Map<String, Object> payload) {

        Object noteObj = payload.get("note");
        double note;

        if (noteObj instanceof Integer) {
            note = ((Integer) noteObj).doubleValue();
        } else if (noteObj instanceof Double) {
            note = (Double) noteObj;
        } else {
            throw new IllegalArgumentException("Le type de la note est incorrect.");
        }

        String commentaire = (String) payload.get("commentaire");

        Fournisseur fournisseurMisAJour = fournisseurService.ajouterNotation(id, note, commentaire);
        return ResponseEntity.ok(fournisseurMisAJour);
    }

    @GetMapping("/top-rated")
    public ResponseEntity<List<Fournisseur>> getTopRatedFournisseurs() {
        List<Fournisseur> fournisseurs = fournisseurService.findAll();
        fournisseurs.sort((f1, f2) -> Double.compare(f2.getNoteMoyenne(), f1.getNoteMoyenne()));
        List<Fournisseur> topRatedFournisseurs = fournisseurs.stream().limit(10).toList();
        return ResponseEntity.ok(topRatedFournisseurs);
    }

    //

    @PostMapping("/{id}/upload-contrat")
    public ResponseEntity<String> uploadContrat(@PathVariable int id, @RequestParam("file") MultipartFile file) throws IOException {
        Fournisseur fournisseur = fournisseurService.getFournisseurById(id);
        fournisseur.setContrat(file.getBytes());
        fournisseurService.saveFournisseur(fournisseur);
        return ResponseEntity.ok("Contrat uploadé avec succès.");
    }

    @GetMapping("/{id}/contrat")
    public ResponseEntity<byte[]> getContrat(@PathVariable int id) {
        Optional<Fournisseur> fournisseur = fournisseurService.findById(id);
        byte[] fileData = fournisseurService.getContrat(id);

        if (fileData == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF).body(fileData);
    }

    @DeleteMapping("/{id}/contrat")
    public ResponseEntity<String> deleteContrat(@PathVariable int id) {
        try {
            fournisseurService.deleteContrat(id);
            return ResponseEntity.ok("Contrat supprimé avec succès.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fournisseur non trouvé.");
        }
    }



}
