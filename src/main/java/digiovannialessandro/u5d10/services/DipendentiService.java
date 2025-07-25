package digiovannialessandro.u5d10.services;

import digiovannialessandro.u5d10.ecxeptions.BadRequestException;
import digiovannialessandro.u5d10.ecxeptions.ValidationException;
import digiovannialessandro.u5d10.entities.Dipendente;
import digiovannialessandro.u5d10.payloads.DipendentiPayload;
import digiovannialessandro.u5d10.repositories.DipendentiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DipendentiService {
    @Autowired
    private DipendentiRepository dipendentiRepository;

    public Dipendente save(DipendentiPayload payload) {

        if (dipendentiRepository.existsByEmail(payload.email())) {
            throw new BadRequestException("Email '" + payload.email() + "' già in uso.");
        }

        if (dipendentiRepository.existsByUsername(payload.username())) {
            throw new BadRequestException("Username '" + payload.username() + "' già in uso.");
        }
        Dipendente newDipendente = new Dipendente(payload.username(), payload.name(), payload.surname(), payload.email());
        Dipendente dipendenteSalvato=this.dipendentiRepository.save(newDipendente);
        return dipendenteSalvato;
    }
}
