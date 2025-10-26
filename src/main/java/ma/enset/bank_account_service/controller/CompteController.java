package ma.enset.bank_account_service.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import ma.enset.bank_account_service.dto.RequestCompteDto;
import ma.enset.bank_account_service.dto.ResponseCompteDto;
import ma.enset.bank_account_service.entities.Compte;
import ma.enset.bank_account_service.mappers.CompteMapper;
import ma.enset.bank_account_service.service.CompteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/comptes")
@Tag(name = "Compte API", description = "Gestion des comptes bancaires avec CRUD et opérations crédit/débit")
public class CompteController {

    private final CompteService compteService;

    public CompteController(CompteService compteService) {
        this.compteService = compteService;
    }

    @PostMapping
    @Operation(
            summary = "Créer un compte",
            description = "Permet de créer un nouveau compte bancaire",
            requestBody = @RequestBody(
                    description = "Données du compte à créer",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = RequestCompteDto.class),
                            examples = @ExampleObject(value = "{ \"nom\": \"Ali\", \"tel\": \"0612345678\", \"solde\": 1000 }")
                    )
            )
    )
    public ResponseCompteDto addCompte(@RequestBody RequestCompteDto dto) {
        Compte compte = CompteMapper.toEntity(dto);
        return CompteMapper.toDto(compteService.addCompte(compte));
    }

    @GetMapping
    @Operation(summary = "Lister tous les comptes", description = "Retourne la liste de tous les comptes existants")
    public List<ResponseCompteDto> getAllComptes() {
        return compteService.getAllComptes()
                .stream()
                .map(CompteMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtenir un compte par ID", description = "Retourne un compte spécifique par son identifiant")
    public ResponseCompteDto getCompte(@PathVariable Long id) {
        Compte compte = compteService.getCompteById(id).orElseThrow();
        return CompteMapper.toDto(compte);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Mettre à jour un compte", description = "Met à jour les informations d'un compte existant")
    public ResponseCompteDto updateCompte(@PathVariable Long id, @RequestBody RequestCompteDto dto) {
        Compte compte = CompteMapper.toEntity(dto);
        return CompteMapper.toDto(compteService.updateCompte(id, compte));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un compte", description = "Supprime un compte existant par son identifiant")
    public void deleteCompte(@PathVariable Long id) {
        compteService.deleteCompte(id);
    }

    @PostMapping("/{id}/credit")
    @Operation(summary = "Créditer un compte", description = "Ajoute un montant au solde du compte")
    public ResponseCompteDto credit(@PathVariable Long id, @RequestParam double montant) {
        return CompteMapper.toDto(compteService.credit(id, montant));
    }

    @PostMapping("/{id}/debit")
    @Operation(summary = "Débiter un compte", description = "Soustrait un montant du solde du compte")
    public ResponseCompteDto debit(@PathVariable Long id, @RequestParam double montant) {
        return CompteMapper.toDto(compteService.debit(id, montant));
    }
}
