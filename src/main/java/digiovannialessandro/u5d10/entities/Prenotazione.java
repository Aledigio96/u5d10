package digiovannialessandro.u5d10.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "prenotazioni")
public class Prenotazione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate dataDiRichiesta;
    @ManyToOne
    @JoinColumn(name = "viaggioId")
    private Viaggio viaggio;
    @ManyToOne
    @JoinColumn(name = "dipendenteId")
    private Dipendente dipendente;
    private String nota;

    public Prenotazione() {
    }

    public Prenotazione(LocalDate dataDiRichiesta, Viaggio viaggio, Dipendente dipendente,String nota) {
        this.dataDiRichiesta = dataDiRichiesta;
        this.viaggio = viaggio;
        this.dipendente = dipendente;
        this.nota=nota;
    }

    public Dipendente getDipendente() {
        return dipendente;
    }

    public Viaggio getViaggio() {
        return viaggio;
    }

    public LocalDate getDataDiRichiesta() {
        return dataDiRichiesta;
    }

    public int getId() {
        return id;
    }

    public void setDataDiRichiesta(LocalDate dataDiRichiesta) {
        this.dataDiRichiesta = dataDiRichiesta;
    }

    public void setDipendente(Dipendente dipendente) {
        this.dipendente = dipendente;
    }

    public void setViaggio(Viaggio viaggio) {
        this.viaggio = viaggio;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }
}
