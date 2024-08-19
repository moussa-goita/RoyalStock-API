package com.test.services;

import com.test.entities.Categorie;
import com.test.entities.Utilisateur;
import com.test.repositories.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategorieService {

    @Autowired
    private CategorieRepository categorieRepository;

    public List<Categorie> findAll() {
        return categorieRepository.findAll();
    }

    public Optional<Categorie> findById(int id) {
        return categorieRepository.findById(id);
    }

    public Categorie save(Categorie categorie) {
        return categorieRepository.save(categorie);
    }

    public void deleteById(int id) {
        categorieRepository.deleteById(id);
    }

    public List<Categorie> findByEntrepotId(int entrepotId) {
        return categorieRepository.findByEntrepotId(entrepotId);
    }

    //Nombre de categories
    public long countCategories() {
        return categorieRepository.countCategories();
    }

    /*Catgories d'une entrepot
    public long countCategoriesByEntrepotId(int entrepotId) {
        return categorieRepository.countCategoriesByEntrepotId(entrepotId);
    }

    public List<Categorie> getCategoriesByEntrepot(int entrepotId) {
        return categorieRepository.findByEntrepotId(entrepotId);
    }*/
}
