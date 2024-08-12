package com.test.services;

import com.test.entities.Fournisseur;
import com.test.entities.StatutFournisseur;
import com.test.repositories.FournisseurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<Fournisseur> findPublicFournisseurs() {
        return fournisseurRepository.findByStatutFournisseur(StatutFournisseur.PUBLIC);
    }

//    public List<Fournisseur> findTopRatedFournisseurs() {
//        List<Fournisseur> fournisseurs = fournisseurRepository.findByStatutFournisseur(StatutFournisseur.PUBLIC);
//        return fournisseurs.stream()
//                .filter(f -> f.getMoyenneNote() >= 4.5)
//                .collect(Collectors.toList());
//    }
}
