package com.test.dto;

import lombok.Data;

@Data
public class TopVenduDTO {
  private String nom;
  private String description;
  private long nbre;

  public TopVenduDTO(String productName, String description, long nbre) {
    this.nom = productName;
    this.description = description;
    this.nbre = nbre;
  }
}
