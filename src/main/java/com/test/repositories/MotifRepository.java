package com.test.repositories;

import com.test.entities.Categorie;
import com.test.entities.Motif;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MotifRepository extends JpaRepository<Motif, Integer> {
    @Query("SELECT COUNT(*) FROM Motif")
    int countMotifs();
    Motif findByTitle(String title);

    List<Motif> findByEntrepotId(int entrepotId);
}
