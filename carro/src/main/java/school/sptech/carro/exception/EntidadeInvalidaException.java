package school.sptech.carro.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EntidadeInvalidaException extends RuntimeException {
    public EntidadeInvalidaException(String message) {
        super(message);
    }
}
