package com.test.repositories;

import com.test.entities.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProduitRepository extends JpaRepository<Produit, Integer> {
    @Query("SELECT p.categorie.name, COUNT(p) FROM Produit p GROUP BY p.categorie.name")
    List<Object[]> countByCategory();

    List<Produit> findByEntrepotId(int entrepotId);
    Produit findByproductName(String nom);
}
