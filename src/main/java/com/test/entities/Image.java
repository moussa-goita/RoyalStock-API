package com.test.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "imageData")
@Data
@Builder
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String filename;
    private String filetype;

    @Lob
    @Column(name = "imagedata", length = 100000)
    private byte[] imageData;

    @OneToMany(mappedBy = "imageContrat")
    List<Fournisseur> fournisseurList;
}
