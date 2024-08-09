package com.test.controllers;

import com.test.entities.DetailEntree;
import com.test.services.DetailEntreeService;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/details-entrees")
public class DetailEntreeController {

    @Autowired
    private DetailEntreeService detailEntreeService;

    @GetMapping
    public List<DetailEntree> getAllDetailsEntrees() {
        return detailEntreeService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailEntree> getDetailsEntreeById(@PathVariable int id) {
        return detailEntreeService.findById(id)
                .map(detailsEntree -> ResponseEntity.ok().body(detailsEntree))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public DetailEntree createDetailsEntree(@RequestBody DetailEntree detailsEntree) {
        return detailEntreeService.save(detailsEntree);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetailEntree> updateDetailsEntree(@PathVariable int id, @RequestBody DetailEntree detailEntreeDetails) {
        return detailEntreeService.findById(id)
                .map(detailsEntree -> {
                    detailsEntree.setQuantite(detailEntreeDetails.getQuantite());
                    detailsEntree.setPrix(detailEntreeDetails.getPrix());
                    detailsEntree.setProduit(detailEntreeDetails.getProduit());
                    detailsEntree.setBonEntree(detailEntreeDetails.getBonEntree());
                    DetailEntree updatedDetailsEntree = detailEntreeService.save(detailsEntree);
                    return ResponseEntity.ok().body(updatedDetailsEntree);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDetailsEntree(@PathVariable int id) {
        detailEntreeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
