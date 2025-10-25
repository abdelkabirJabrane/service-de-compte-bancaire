package ma.enset.bank_account_service.service;


import ma.enset.bank_account_service.entities.Compte;
import ma.enset.bank_account_service.repository.CompteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompteService {

    private final CompteRepository compteRepository;

    public CompteService(CompteRepository compteRepository) {
        this.compteRepository = compteRepository;
    }

    public Compte addCompte(Compte compte) {
        return compteRepository.save(compte);
    }

    public List<Compte> getAllComptes() {
        return compteRepository.findAll();
    }

    public Optional<Compte> getCompteById(Long id) {
        return compteRepository.findById(id);
    }

    public Compte updateCompte(Long id, Compte compteDetails) {
        Compte compte = compteRepository.findById(id).orElseThrow();
        compte.setNom(compteDetails.getNom());
        compte.setTel(compteDetails.getTel());
        compte.setSolde(compteDetails.getSolde());
        return compteRepository.save(compte);
    }

    public void deleteCompte(Long id) {
        compteRepository.deleteById(id);
    }

    public Compte credit(Long id, double montant) {
        Compte compte = compteRepository.findById(id).orElseThrow();
        compte.setSolde(compte.getSolde() + montant);
        return compteRepository.save(compte);
    }

    public Compte debit(Long id, double montant) {
        Compte compte = compteRepository.findById(id).orElseThrow();
        if(compte.getSolde() < montant) {
            throw new RuntimeException("Solde insuffisant");
        }
        compte.setSolde(compte.getSolde() - montant);
        return compteRepository.save(compte);
    }
}
