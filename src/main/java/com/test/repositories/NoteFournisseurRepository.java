package com.test.repositories;

import com.test.entities.NoteFournisseur;
import com.test.entities.Fournisseur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NoteFournisseurRepository extends JpaRepository<NoteFournisseur, Integer> {

    // Trouver toutes les notes pour un fournisseur donné
    List<NoteFournisseur> findByFournisseur(Fournisseur fournisseur);

    // Calculer la moyenne des notes pour un fournisseur donné
    @Query("SELECT AVG(n.note) FROM NoteFournisseur n WHERE n.fournisseur = :fournisseur")
    Double calculateAverageNoteForFournisseur(@Param("fournisseur") Fournisseur fournisseur);

    // Obtenir le nombre total de notes pour un fournisseur donné
    @Query("SELECT COUNT(n) FROM NoteFournisseur n WHERE n.fournisseur = :fournisseur")
    Long countNotesForFournisseur(@Param("fournisseur") Fournisseur fournisseur);
}
