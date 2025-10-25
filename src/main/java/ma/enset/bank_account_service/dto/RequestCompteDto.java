package ma.enset.bank_account_service.dto;

import lombok.Data;

@Data
// Request DTO
public class RequestCompteDto {
    private String nom;
    private String tel;
    private double solde;
    // Getters/Setters
}

// Response DTO

