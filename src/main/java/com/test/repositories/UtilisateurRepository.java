package com.test.repositories;

import com.test.entities.Entrepot;
import com.test.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
    Optional<Utilisateur> findByUsername(String username);
    Optional<Utilisateur> findByEmail(String email);
    List<Utilisateur> findByEntrepotId(int entrepotId);
    @Query("SELECT COUNT(u) FROM Utilisateur u WHERE u.role.name = :roleName AND u.entrepot.Id = :entrepotId")
    int countByRoleAndEntrepotId(@Param("roleName") String roleName, @Param("entrepotId") Integer entrepotId);

    @Query("SELECT u FROM Utilisateur u WHERE u.entrepot.Id = :entrepotId AND u.role.name = 'MANAGER'")
    Optional<Utilisateur> findManagerByEntrepot(@Param("entrepotId") int entrepotId);

    List<Utilisateur> findAllByEntrepot(Entrepot entrepot);
}
