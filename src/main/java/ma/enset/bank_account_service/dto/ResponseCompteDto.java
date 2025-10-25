package ma.enset.bank_account_service.dto;

import lombok.Data;

@Data
public class ResponseCompteDto {
    private Long id;
    private String nom;
    private String tel;
    private double solde;
    // Getters/Setters
}
