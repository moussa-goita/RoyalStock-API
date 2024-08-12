package com.test.controllers;

import com.test.entities.NoteFournisseur;
import com.test.services.NoteFournisseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notes")
public class NoteFournisseurController {

    @Autowired
    private NoteFournisseurService noteFournisseurService;

    @PostMapping("/ajouter")
    public ResponseEntity<NoteFournisseur> ajouterNote(@RequestBody NoteFournisseur noteFournisseur) {
        try {
            NoteFournisseur savedNote = noteFournisseurService.ajouterNote(noteFournisseur);
            return ResponseEntity.ok(savedNote);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);  // Si la note n'est pas valide (hors de 1 à 5)
        }
    }
}
