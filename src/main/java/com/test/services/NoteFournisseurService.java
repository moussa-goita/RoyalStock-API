package com.test.services;

import com.test.entities.Fournisseur;
import com.test.entities.NoteFournisseur;
import com.test.repositories.FournisseurRepository;
import com.test.repositories.NoteFournisseurRepository;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

//import static jdk.javadoc.internal.doclets.formats.html.markup.HtmlStyle.notes;

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
        // Sauvegarde de la note
//Fournisseur fournisseur = fournisseurRepository.findById(noteFournisseur.getId()).orElseThrow(()-> new RuntimeException("mauvais fournisseurs"));
//
//            noteFournisseur.setFournisseur(fournisseur);
//        // Calcul de la nouvelle moyenne de notes du fournisseur
//        Fournisseur fournisseur = savedNote.getFournisseur();
//        fournisseurService.calculerMoyenneNote();

        // Mise à jour du fournisseur avec la nouvelle moyenne
//        fournisseurRepository.save(fournisseur);

        return noteFournisseurRepository.save(noteFournisseur);
    }
//    // Méthode pour calculer la moyenne des notes
//    public double calculerMoyenneNote() {
//        if (notes == null || notes.isEmpty()) {
//            return 0.0;
//        }
//        double sum = 0;
//        for (NoteFournisseur note : notes) {
//            sum += note.getNote();
//        }
//        return sum / notes.size();
//    }
//
//    // Mettre à jour la moyenne des notes
//    @PrePersist
//    @PreUpdate
//    public void updateMoyenneNote() {
//        this.calculerMoyenneNote();
//    }
}
