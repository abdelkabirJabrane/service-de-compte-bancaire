package ma.enset.bank_account_service.controller;




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
public class CompteController {

    private final CompteService compteService;

    public CompteController(CompteService compteService) {
        this.compteService = compteService;
    }

    @PostMapping
    public ResponseCompteDto addCompte(@RequestBody RequestCompteDto dto) {
        Compte compte = CompteMapper.toEntity(dto);
        return CompteMapper.toDto(compteService.addCompte(compte));
    }

    @GetMapping
    public List<ResponseCompteDto> getAllComptes() {
        return compteService.getAllComptes()
                .stream()
                .map(CompteMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseCompteDto getCompte(@PathVariable Long id) {
        Compte compte = compteService.getCompteById(id).orElseThrow();
        return CompteMapper.toDto(compte);
    }

    @PutMapping("/{id}")
    public ResponseCompteDto updateCompte(@PathVariable Long id, @RequestBody RequestCompteDto dto) {
        Compte compte = CompteMapper.toEntity(dto);
        return CompteMapper.toDto(compteService.updateCompte(id, compte));
    }

    @DeleteMapping("/{id}")
    public void deleteCompte(@PathVariable Long id) {
        compteService.deleteCompte(id);
    }

    @PostMapping("/{id}/credit")
    public ResponseCompteDto credit(@PathVariable Long id, @RequestParam double montant) {
        return CompteMapper.toDto(compteService.credit(id, montant));
    }

    @PostMapping("/{id}/debit")
    public ResponseCompteDto debit(@PathVariable Long id, @RequestParam double montant) {
        return CompteMapper.toDto(compteService.debit(id, montant));
    }
}

