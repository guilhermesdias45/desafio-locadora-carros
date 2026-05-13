package school.sptech.aluguel.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EntidadeInvalidoException extends RuntimeException {
    public EntidadeInvalidoException(String message) {
        super(message);
    }
}
