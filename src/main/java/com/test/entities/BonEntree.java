package com.test.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bon_entrees")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BonEntree {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "date_commande", nullable = false)
    private LocalDate dateCommande;

    @Column(name = "statut")
    private String statut;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id", nullable = false)
    private Utilisateur utilisateur;

    @ManyToOne
    @JoinColumn(name = "fournisseur_id", nullable = false)
    private Fournisseur fournisseur;

    @OneToMany(mappedBy = "bonEntree", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<DetailEntree> detailEntrees;

    @ManyToOne
    @JoinColumn(name = "entrepot_id", nullable = false)
    private Entrepot entrepot;
}
