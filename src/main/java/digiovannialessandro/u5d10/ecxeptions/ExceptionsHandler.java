package digiovannialessandro.u5d10.ecxeptions;

import digiovannialessandro.u5d10.payloads.ErrorsDTO;
import digiovannialessandro.u5d10.payloads.ErrorsWithListDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorsWithListDTO handleValidationErrors(ValidationException ex) {

        return new ErrorsWithListDTO(ex.getMessage(), LocalDateTime.now(), ex.getErrorMessages());
    }

    @ExceptionHandler(BadRequestException.class) // Tra le parentesi indico quale eccezione dovrà gestire questo metodo
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
    public ErrorsDTO handleBadRequest(BadRequestException ex) {
        return new ErrorsDTO(ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND) // 404
    public ErrorsDTO handleNotFound(NotFoundException ex) {
        return new ErrorsDTO(ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(Exception.class) // Tutte le eccezioni non gestite dagli altri metodi risulteranno essere un Server Error (500)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // 500
    public ErrorsDTO handleServerError(Exception ex) {
        ex.printStackTrace(); // E' importante avere il print dello stack trace per avere un'indicazione utile per debuggare/fixare l'errore
        return new ErrorsDTO("C'è stato un errore generico! Giuro che lo risolveremo presto!", LocalDateTime.now());
    }

}
