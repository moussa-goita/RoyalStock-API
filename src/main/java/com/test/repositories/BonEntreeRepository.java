package com.test.repositories;

import com.test.entities.BonEntree;
import com.test.entities.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BonEntreeRepository extends JpaRepository<BonEntree, Integer> {
    @Query("SELECT MONTH(be.dateCommande), COUNT(be) FROM BonEntree be GROUP BY MONTH(be.dateCommande)")
    List<Object[]> countByMonth();

    List<BonEntree> findAllByEntrepotId(int entrepotId);

}
