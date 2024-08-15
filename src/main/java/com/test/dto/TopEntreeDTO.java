package com.test.dto;

import lombok.Data;

@Data
public class TopEntreeDTO {
  private String nom;
  private String description;
  private long nbre;

  public TopEntreeDTO(String productName, String description, long nbre) {
    this.nom = productName;
    this.description = description;
    this.nbre = nbre;
  }

}
