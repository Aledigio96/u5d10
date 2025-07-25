package digiovannialessandro.u5d10.repositories;

import digiovannialessandro.u5d10.entities.Dipendente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DipendentiRepository extends JpaRepository<Dipendente,Integer> {
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}
