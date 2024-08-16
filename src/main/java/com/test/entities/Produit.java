package com.test.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "produits")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "description")
    private String description;

    @Column(name = "quantity")
    private int quantity;
    @Temporal(TemporalType.DATE)
    private LocalDate dateExpiration;

    @Column(name = "seuil")
    private int seuil= 0;

    @ManyToOne
    @JoinColumn(name = "categories_id", nullable = false)
    private Categorie categorie;

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private Utilisateur createdBy;

    @ManyToOne
    @JoinColumn(name = "entrepot_id", nullable = false)
    private Entrepot entrepot;
    @Column(name = "qr_code", nullable = false)
    private String qrCode;
}
