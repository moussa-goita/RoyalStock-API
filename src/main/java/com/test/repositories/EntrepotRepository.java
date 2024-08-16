package com.test.repositories;

import com.test.entities.Entrepot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EntrepotRepository extends JpaRepository<Entrepot, Integer> {

    // Méthode pour rechercher les entrepôts à proximité d'une localisation donnée
    @Query(value = "SELECT * FROM entrepots WHERE ST_Distance_Sphere(location, ST_GeomFromText(:location, 4326)) <= :distance", nativeQuery = true)
    List<Entrepot> findByLocationNear(@Param("location") String location, @Param("distance") double distance);
}
