package ma.enset.bank_account_service.entities;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@Schema(description = "Représentation d'un compte bancaire")
public class Compte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identifiant unique du compte", example = "1")
    private Long id;

    @Schema(description = "Nom du titulaire du compte", example = "Ali")
    private String nom;

    @Schema(description = "Numéro de téléphone du titulaire", example = "0612345678")
    private String tel;

    @Schema(description = "Solde du compte", example = "1000.0")
    private double solde;

    public Compte() {}
    public Compte(String nom, String tel, double solde) {
        this.nom = nom;
        this.tel = tel;
        this.solde = solde;
    }

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getTel() { return tel; }
    public void setTel(String tel) { this.tel = tel; }
    public double getSolde() { return solde; }
    public void setSolde(double solde) { this.solde = solde; }
}


