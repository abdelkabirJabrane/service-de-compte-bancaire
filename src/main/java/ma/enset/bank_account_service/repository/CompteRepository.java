package ma.enset.bank_account_service.repository;



import ma.enset.bank_account_service.entities.Compte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompteRepository extends JpaRepository<Compte, Long> {
}
