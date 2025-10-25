package ma.enset.bank_account_service.mappers;


import lombok.Data;
import ma.enset.bank_account_service.dto.RequestCompteDto;
import ma.enset.bank_account_service.dto.ResponseCompteDto;
import ma.enset.bank_account_service.entities.Compte;

public class CompteMapper {

    public static Compte toEntity(RequestCompteDto dto) {
        Compte compte = new Compte();
        compte.setNom(dto.getNom());
        compte.setTel(dto.getTel());
        compte.setSolde(dto.getSolde());
        return compte;
    }

    public static ResponseCompteDto toDto(Compte compte) {
        ResponseCompteDto dto = new ResponseCompteDto();
        dto.setId(compte.getId());
        dto.setNom(compte.getNom());
        dto.setTel(compte.getTel());
        dto.setSolde(compte.getSolde());
        return dto;
    }
}
