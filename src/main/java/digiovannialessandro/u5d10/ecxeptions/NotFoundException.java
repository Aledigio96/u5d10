package digiovannialessandro.u5d10.ecxeptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(Integer id) {
        super("La risorsa con id " + id + " non è stata trovata!");
    }
}
