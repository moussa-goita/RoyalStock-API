package com.test.repositories;

import com.test.entities.BonEntree;
import com.test.entities.BonSortie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BonSortieRepository extends JpaRepository<BonSortie, Integer> {
    @Query("SELECT MONTH(bs.dateSortie), COUNT(bs) FROM BonSortie bs GROUP BY MONTH(bs.dateSortie)")
    List<Object[]> countByMonth();

    List<BonSortie> findAllByEntrepotId(int entrepotId);

}
