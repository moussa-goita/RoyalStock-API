package com.test.repositories;

import com.test.entities.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CategorieRepository extends JpaRepository<Categorie, Integer> {
    Categorie findByname(String name);

    //Nombre de Catégories
    @Query("SELECT COUNT(*) FROM Categorie")
    int countCategories();
    List<Categorie> findByEntrepotId(int entrepotId);

    /*Categories d'un Entrepot
    @Query("SELECT COUNT(DISTINCT p.categorie) FROM Produit p WHERE p.entrepots.Id = :entrepotId")
    int countCategoriesByEntrepotId(int entrepotId);

    List<Categorie> findByEntrepotId(int entrepotId);*/
}
