package com.test.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "details_sorties")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailSortie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "prix")
    private int prix;

    @ManyToOne
    @JoinColumn(name = "bon_sortie_id", nullable = false)
    @JsonBackReference
    private BonSortie bonSortie;

    @ManyToOne
    @JoinColumn(name = "produit_id")
    private Produit produit;
}
