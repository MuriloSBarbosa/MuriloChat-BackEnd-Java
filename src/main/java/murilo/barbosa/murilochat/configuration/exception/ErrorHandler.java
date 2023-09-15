package murilo.barbosa.murilochat.configuration.exception;

import io.jsonwebtoken.MalformedJwtException;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(MalformedJwtException.class)
    public ResponseEntity<Object> tratarErro404() {
        return ResponseEntity.status(400).build();
    }

}
