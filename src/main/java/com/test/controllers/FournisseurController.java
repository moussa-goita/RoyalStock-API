package com.test.controllers;

import com.test.entities.Fournisseur;
import com.test.services.FournisseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/fournisseur")
public class FournisseurController {

    @Autowired
    private FournisseurService fournisseurService;

    @GetMapping("/{id}")
    public Optional<Fournisseur> getFournisseur(@PathVariable int id) {
        return fournisseurService.findById(id);
    }

    @PostMapping("/create")
    public Fournisseur createFournisseur(@RequestBody Fournisseur fournisseur) {
        return fournisseurService.save(fournisseur);
    }
    @GetMapping("/{id}/moyenne-notes")
    public ResponseEntity<Double> getMoyenneNotes(@PathVariable int id) {
        try {
            double moyenneNotes = fournisseurService.getMoyenneNotes(id);
            return ResponseEntity.ok(moyenneNotes);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/update/{id}")
    public Fournisseur updateFournisseur(@PathVariable int id, @RequestBody Fournisseur fournisseur) {
        fournisseur.setId(id);
        return fournisseurService.save(fournisseur);
    }

    @DeleteMapping("/{id}")
    public void deleteFournisseur(@PathVariable int id) {
        fournisseurService.deleteById(id);
    }

    @GetMapping("/count")
    public long getFournisseursCount() {
        return fournisseurService.getFournisseursCount();
    }

    @GetMapping("/entrepot/{entrepotId}")
    public List<Fournisseur> getFournisseursByEntrepot(@PathVariable int entrepotId) {
        return fournisseurService.findByEntrepotId(entrepotId);
    }

    @GetMapping("/public")
    public List<Fournisseur> getPublicFournisseurs() {
        return fournisseurService.findPublicFournisseurs();
    }

}
