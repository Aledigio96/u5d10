package digiovannialessandro.u5d10.repositories;

import digiovannialessandro.u5d10.entities.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface PrenotazioniRepository extends JpaRepository<Prenotazione,Integer> {
    boolean existsByDipendente_IdAndDataDiRichiesta(int dipendenteId, LocalDate dataDiRichiesta);
}
