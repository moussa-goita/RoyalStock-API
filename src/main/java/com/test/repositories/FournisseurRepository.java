package com.test.repositories;

import com.test.entities.Fournisseur;
import com.test.entities.Statut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FournisseurRepository extends JpaRepository<Fournisseur, Integer> {
    @Query("SELECT COUNT(*) FROM Fournisseur")
    int countFournisseurs();

    List<Fournisseur> findByEntrepotId(int entrepotId);

<<<<<<< HEAD
    Optional<Fournisseur> findById(Long id);
=======
    Optional<Fournisseur> findById(int id);
>>>>>>> 191a014 (Correction by chef)

    List<Fournisseur> findAllByStatut(Statut statut);


}
