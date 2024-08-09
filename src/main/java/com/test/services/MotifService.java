package com.test.services;

import com.test.entities.Categorie;
import com.test.entities.Entrepot;
import com.test.entities.Motif;
import com.test.entities.Utilisateur;
import com.test.exception.MotifNotFoundException;
import com.test.repositories.CategorieRepository;
import com.test.repositories.MotifRepository;
import com.test.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MotifService {

  @Autowired
  private MotifRepository motifRepository;

  public List<Motif> findAll() {
    return motifRepository.findAll();
  }

  public Optional<Motif> findById(int id) {
    return motifRepository.findById(id);
  }

  public Motif save(Motif motif) {
    return motifRepository.save(motif);
  }

  public void deleteById(int id) {
    motifRepository.deleteById(id);
  }

  public int getNombreMotif() {
  return motifRepository.countMotifs();
  }

  public List<Motif> findByEntrepotId(int entrepotId) {
    return motifRepository.findByEntrepotId(entrepotId);
  }
}
