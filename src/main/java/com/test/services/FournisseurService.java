package com.test.services;

import com.test.entities.Fournisseur;
import com.test.entities.StatutFournisseur;
import com.test.repositories.FournisseurRepository;
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

    public long getFournisseursCount() {
        return fournisseurRepository.count();
    }

    public List<Fournisseur> findByEntrepotId(int entrepotId) {
        return fournisseurRepository.findByEntrepotId(entrepotId);
    }
    // Méthode pour obtenir la note moyenne d'un fournisseur par ID
    public double getMoyenneNotes(int fournisseurId) {
        Double moyenneNotes = fournisseurRepository.findMoyenneNotesByFournisseurId(fournisseurId);
        if (moyenneNotes != null) {
            return moyenneNotes;
        } else {
            throw new RuntimeException("Fournisseur non trouvé ou sans notes avec l'ID : " + fournisseurId);
        }
    }

    public List<Fournisseur> findPublicFournisseurs() {
        return fournisseurRepository.findByStatutFournisseur(StatutFournisseur.PUBLIC);
    }

}
