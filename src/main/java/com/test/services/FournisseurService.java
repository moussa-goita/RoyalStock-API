package com.test.services;

import com.test.entities.Categorie;
import com.test.entities.Fournisseur;
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

    public int getFournisseursCount() {
        return fournisseurRepository.countFournisseurs();
    }

    public List<Fournisseur> findByEntrepotId(int entrepotId) {
        return fournisseurRepository.findByEntrepotId(entrepotId);
    }
}

