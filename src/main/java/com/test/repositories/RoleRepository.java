package com.test.repositories;

import com.test.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(String Name);

    //Nombre de Role
    @Query("SELECT COUNT(*) FROM Role")
    int countRole();
}
