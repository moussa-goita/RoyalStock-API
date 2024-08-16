package com.test.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "notifications")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String message;
    private String type;
    private boolean isRead = false;

    @ManyToOne
    @JoinColumn(name = "produit_id")
    private Produit produit;

    @Temporal(TemporalType.DATE)
    @Column(name = "create_at", nullable = false, updatable = false)
    private Date createDay = new Date();

    @ManyToOne
    @JoinColumn(name = "utilisateur_id", nullable = false)
    @JsonBackReference
    private Utilisateur utilisateur;

    @ManyToOne
    @JoinColumn(name = "entrepots_id")
    private Entrepot entrepot;
}
