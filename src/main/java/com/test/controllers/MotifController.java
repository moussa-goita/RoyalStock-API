package com.test.controllers;


import com.test.entities.Motif;
import com.test.entities.Utilisateur;
import com.test.services.MotifService;
import com.test.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/motif")
public class MotifController {

    @Autowired
    private MotifService motifService;

    @Autowired
    private UtilisateurService utilisateurService;

    //Nombre de Motif
    @GetMapping("/motifNombre")
    public int getMotifNombre() {
        return motifService.getNombreMotif();
    }

    @GetMapping
    public List<Motif> getMotif() {
        return motifService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Motif> findById(@PathVariable int id) {
        return motifService.findById(id)
                .map(motif -> ResponseEntity.ok().body(motif))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Motif save(@RequestBody Motif motif) {
        return motifService.save(motif);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Motif> save(@PathVariable int id, @RequestBody Motif motifDetails) {
        return motifService.findById(id)
                .map(motif -> {
                    motif.setTitle(motifDetails.getTitle());
                    motif.setCreatedBy(motifDetails.getCreatedBy());
                    Motif updateMotif = motifService.save(motif);
                    return ResponseEntity.ok().body(updateMotif);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable int id) {
        motifService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/current")
    public ResponseEntity<List<Motif>> getMotifsForCurrentUser(@RequestParam String email) {
        Utilisateur utilisateur = utilisateurService.findByEmail(email).orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        List<Motif> motifServiceByEntrepotId = motifService.findByEntrepotId(utilisateur.getEntrepot().getId());
        return ResponseEntity.ok(motifServiceByEntrepotId);
    }

    @PostMapping("/create")
    public ResponseEntity<Motif> createMotif(@RequestBody Map<String, String> motifData, @RequestParam String email) {
        Utilisateur utilisateur = utilisateurService.findByEmail(email).orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        Motif motif = new Motif();
        motif.setTitle(motifData.get("name"));
        motif.setCreatedBy(utilisateur);
        motif.setEntrepot(utilisateur.getEntrepot());
        Motif savedCategorie = motifService.save(motif);
        return ResponseEntity.ok(savedCategorie);
    }
}
