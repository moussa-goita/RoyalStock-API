package com.test.services;

import com.test.entities.Categorie;
import com.test.entities.Fournisseur;
import com.test.entities.Statut;
import com.test.repositories.FournisseurRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FournisseurService {

    @Autowired
    private FournisseurRepository fournisseurRepository;

    public List<Fournisseur> findAll() {
        return fournisseurRepository.findAll();
    }

    public Optional<Fournisseur> findById(int id) {
        return fournisseurRepository.findById(id);
    }

    public Fournisseur save(Fournisseur fournisseur) {
        return fournisseurRepository.save(fournisseur);
    }

    public void deleteById(int id) {
        fournisseurRepository.deleteById(id);
    }

    public int getFournisseursCount() {
        return fournisseurRepository.countFournisseurs();
    }

    public List<Fournisseur> findByEntrepotId(int entrepotId) {
        return fournisseurRepository.findByEntrepotId(entrepotId);
    }

<<<<<<< HEAD
    //

    public Fournisseur mettreFournisseurPublic(Long fournisseurId) {
        Fournisseur fournisseur = fournisseurRepository.findById(fournisseurId)
                .orElseThrow(() -> new EntityNotFoundException("Fournisseur non trouvé avec l'ID: " + fournisseurId));

        fournisseur.setStatut(Statut.PUBLIC); // Changer le statut à PUBLIC
        return fournisseurRepository.save(fournisseur);
=======
    public Fournisseur mettreFournisseurPublic(int id) {
        Fournisseur fournisseur = findById(id)
                .orElseThrow(() -> new RuntimeException("Fournisseur non trouvé"));
        fournisseur.setStatut(Statut.PUBLIC);
        return save(fournisseur);
>>>>>>> 191a014 (Correction by chef)
    }

    public List<Fournisseur> fourPublic(){
        return fournisseurRepository.findAllByStatut(Statut.PUBLIC);
    }

<<<<<<< HEAD
    //

    public Fournisseur ajouterNotation(int fournisseurId, double note) {
=======
    public Fournisseur ajouterNotation(int fournisseurId, double note) {
        if (note < 1 || note > 5) {
            throw new IllegalArgumentException("La note doit être entre 1 et 5.");
        }

>>>>>>> 191a014 (Correction by chef)
        Fournisseur fournisseur = fournisseurRepository.findById(fournisseurId)
                .orElseThrow(() -> new EntityNotFoundException("Fournisseur non trouvé avec l'ID: " + fournisseurId));

        int nombreNotesActuel = fournisseur.getNombreNotes();
        double noteMoyenneActuelle = fournisseur.getNoteMoyenne();
        double nouvelleNote = ((noteMoyenneActuelle * nombreNotesActuel) + note) / (nombreNotesActuel + 1);

        fournisseur.setNoteMoyenne(nouvelleNote);
        fournisseur.setNombreNotes(nombreNotesActuel + 1);

        return fournisseurRepository.save(fournisseur);
    }
<<<<<<< HEAD
=======

>>>>>>> 191a014 (Correction by chef)
}

