package com.test.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "fournisseurs")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Fournisseur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "fourn_name", nullable = false)
    private String fournName;

    @Column(name = "adresse")
    private String adresse;

    @Column(name = "telephone")
    private String telephone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", nullable = false)
    private Utilisateur createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entrepot_id", nullable = false)
    private Entrepot entrepot;

    @Enumerated(EnumType.STRING)
    @Column(name = "statut_fournisseur")
    private StatutFournisseur statutFournisseur;

    @Column(name = "domaine_activite")
    private String domaineActivite;

    @Column(name = "contrat")
    private String contrat;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<NoteFournisseur> notes = new ArrayList<>();



}
