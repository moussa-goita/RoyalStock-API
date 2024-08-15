package com.test.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "DataChart")
public class DataChart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String label;

    @ElementCollection
    private List<Integer> data;

    private String borderColor;

    private boolean fill;

    private double tension;

    @ElementCollection //Utilisé pour indiquer que le champ est une collection d'éléments et doit être mappé dans une table jointe.
    private List<Integer> borderDash;

    private String backgroundColor;
}

