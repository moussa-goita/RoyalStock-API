package com.test.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "entrepots")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Entrepot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @Column(name = "entrepot_name", nullable = false, unique = true)
    private String entrepotName;

    @Column(name = "lieu")
    private String lieu;

    @Column(name = "statut")
    private String statut;

    @Column(name = "create_at", nullable = false, updatable = false)
    private LocalDate dateCreate;

    @Column(name = "abonnement_start", nullable = false)
    private LocalDate abonnementStart;

    @Column(name = "abonnement_end", nullable = false)
    private LocalDate abonnementEnd;

    //Des attributs pour stocker les coordonnées géographiques dans l'entité Entrepot.
    @Column(name = "latitude", nullable = false)
    private double latitude;  // Nouvelle colonne pour la latitude

    @Column(name = "longitude", nullable = false)
    private double longitude; // Nouvelle colonne pour la longitude

    @Column(columnDefinition = "POINT")
    private Point location; // Nouvelle propriété pour stocker les coordonnées géographiques

}
