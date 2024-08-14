package com.test.repositories;

import com.test.entities.Fournisseur;
import com.test.entities.StatutFournisseur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FournisseurRepository extends JpaRepository<Fournisseur, Integer> {
    @Query("SELECT COUNT(*) FROM Fournisseur")
    int countFournisseurs();

    List<Fournisseur> findByEntrepotId(int entrepotId);

    List<Fournisseur> findByStatutFournisseur(StatutFournisseur statutFournisseur);

    // Requête native pour obtenir la note moyenne d'un fournisseur
    @Query(value = "SELECT AVG(n.note) FROM notes_fournisseurs n WHERE n.fournisseur_id = :fournisseurId", nativeQuery = true)
    Double findMoyenneNotesByFournisseurId(@Param("fournisseurId") int fournisseurId);}
