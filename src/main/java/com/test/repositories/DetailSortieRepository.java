package com.test.repositories;

import com.test.entities.DetailSortie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface DetailSortieRepository extends JpaRepository<DetailSortie, Integer> {

}
