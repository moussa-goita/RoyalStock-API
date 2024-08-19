package com.test.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "fournisseurs")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Fournisseur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @Column(name = "fourn_name", nullable = false)
    private String fournName;

    @Column(name = "adresse")
    private String adresse;

    @Column(name = "telephone")
    private String telephone;

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private Utilisateur CreatedBy;

    @ManyToOne
    @JoinColumn(name = "entrepot_id", nullable = false)
    private Entrepot entrepot;

    @Enumerated(EnumType.STRING)
    private Statut statut;

    // Les autres attributs
    @Column(name = "note_moyenne")
    private double noteMoyenne; // Note moyenne

    @Column(name = "nombre_notes")
    private int nombreNotes; // Nombre total de notes

    @Column(name = "email")
    private String email; // Email du fournisseur

    @Column(name = "commentaire")
    private String commentaire; // Commentaire du fournisseur

    @Column(name = "service")
    private String service; // Description du service fournisseur


}
