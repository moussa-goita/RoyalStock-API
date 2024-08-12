package com.test.repositories;

import com.test.entities.BonEntree;
import com.test.entities.BonSortie;
import com.test.entities.DetailEntree;
import com.test.entities.DetailSortie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface DetailEntreeRepository extends JpaRepository<DetailEntree, Integer> {
    List<DetailEntree> findByBonEntree(BonEntree bonEntree);
}
