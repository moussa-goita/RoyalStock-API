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
import java.util.stream.Collectors;

@Service
public class FournisseurService {

    @Autowired
    private FournisseurRepository fournisseurRepository;
    private int note;

    //Methode pour reccupérer tous les fournisseurs dans la base de données
    public List<Fournisseur> findAll() {
        return fournisseurRepository.findAll();
    }

    //Methode pour reccupérer un fournisseur spécifique par son id
    public Optional<Fournisseur> findById(int id) {
        return fournisseurRepository.findById(id);
    }

    // Methode pour enregistre un fournisseur dans la base de données
    public Fournisseur save(Fournisseur fournisseur) {
        return fournisseurRepository.save(fournisseur);
    }

    // Methode pour supprimer un fournisseur dans la base de données
    public void deleteById(int id) {
        fournisseurRepository.deleteById(id);
    }

    // Methode pour obtenir le nombretotal de fournisseur dans la base de données
    public int getFournisseursCount() {
        return fournisseurRepository.countFournisseurs();
    }

    //Methode pour trouve les fournisseurs associés à un entrepôt spécifique
    public List<Fournisseur> findByEntrepotId(int entrepotId) {
        return fournisseurRepository.findByEntrepotId(entrepotId);
    }

    // Methode pour changer le statut d'un fournisseur
    public Fournisseur mettreFournisseurPublic(Long fournisseurId) {
        Fournisseur fournisseur = fournisseurRepository.findById(fournisseurId)
                .orElseThrow(() -> new EntityNotFoundException("Fournisseur non trouvé avec l'ID: " + fournisseurId));

        fournisseur.setStatut(Statut.PUBLIC); // Changer le statut à PUBLIC
        return fournisseurRepository.save(fournisseur);
    }

    // Methode pour afficher la liste des fournisseurs public
    public List<Fournisseur> fourPublic(){
        return fournisseurRepository.findAllByStatut(Statut.PUBLIC);
    }

    public List<Fournisseur> findAllByNoteMoyenne(double noteMoyenne) {
        // Filtrer les fournisseurs publics par note moyenne
        return fournisseurRepository.findAllByStatut(Statut.PUBLIC).stream()
                .filter(f -> f.getNoteMoyenne() == noteMoyenne)
                .collect(Collectors.toList());
    }

    public List<Fournisseur> findAllByNombreNotes(int nombreNotes) {
        // Filtrer les fournisseurs publics par nombre de notes
        return fournisseurRepository.findAllByStatut(Statut.PUBLIC).stream()
                .filter(f -> f.getNombreNotes() == nombreNotes)
                .collect(Collectors.toList());
    }

    
    //Methode pour noter un fournisseur
    public Fournisseur ajouterNotation(int fournisseurId, double note) {

        if (note < 1 || note > 5) {
            throw new IllegalArgumentException("La note doit être comprise entre 1 et 5");
        }

        Fournisseur fournisseur = fournisseurRepository.findById(fournisseurId)
                .orElseThrow(() -> new EntityNotFoundException("Fournisseur non trouvé avec l'ID: " + fournisseurId));

        int nombreNotesActuel = fournisseur.getNombreNotes();
        double noteMoyenneActuelle = fournisseur.getNoteMoyenne();
        double nouvelleNote = ((noteMoyenneActuelle * nombreNotesActuel) + note) / (nombreNotesActuel + 1);

        fournisseur.setNoteMoyenne(nouvelleNote);
        fournisseur.setNombreNotes(nombreNotesActuel + 1);

        return fournisseurRepository.save(fournisseur);
    }
}

