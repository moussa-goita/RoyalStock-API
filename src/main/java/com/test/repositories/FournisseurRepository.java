package com.test.repositories;

import com.test.entities.Fournisseur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FournisseurRepository extends JpaRepository<Fournisseur, Integer> {
    @Query("SELECT COUNT(*) FROM Fournisseur")
    int countFournisseurs();

    List<Fournisseur> findByEntrepotId(int entrepotId);
}
