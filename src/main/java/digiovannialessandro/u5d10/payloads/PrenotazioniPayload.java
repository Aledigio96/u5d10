package digiovannialessandro.u5d10.payloads;

import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;

public record PrenotazioniPayload (
        @NotEmpty(message = "La data di richiesta è obbligatoria")
        LocalDate dataDiRichiesta,
        int viaggioId,
        int dipendenteId,
        String nota

) {
}
