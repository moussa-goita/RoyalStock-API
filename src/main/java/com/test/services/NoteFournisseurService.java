package com.test.services;

import com.test.entities.NoteFournisseur;
import com.test.repositories.FournisseurRepository;
import com.test.repositories.NoteFournisseurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NoteFournisseurService {

    @Autowired
    private NoteFournisseurRepository noteFournisseurRepository;

    @Autowired
    private FournisseurRepository fournisseurRepository;
    @Autowired
    private FournisseurService fournisseurService;

    @Transactional
    public NoteFournisseur ajouterNote(NoteFournisseur noteFournisseur) {

        return noteFournisseurRepository.save(noteFournisseur);
    }
}
