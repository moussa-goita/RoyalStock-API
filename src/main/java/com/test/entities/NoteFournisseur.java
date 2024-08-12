src/main/java/com/test/entities/NoteFournisseur.javapackage com.test.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "notes_fournisseurs")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoteFournisseur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "fournisseur_id", nullable = false)
    private Fournisseur fournisseur;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id", nullable = false)
    private Utilisateur utilisateur;

    @Column(name = "commentaire", nullable = true)
    private String commentaire;

    @Column(name = "note", nullable = false)
    private int note; // Note sur une échelle de 1 à 5

    // Méthode pour valider la note
    public void setNote(int note) {
        if (note < 1 || note > 5) {
            throw new IllegalArgumentException("La note doit être comprise entre 1 et 5");
        }
        this.note = note;
    }
}
