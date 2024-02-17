package Trendithon.SpinOff.domain.heart.valid;

import Trendithon.SpinOff.domain.heart.valid.exception.HeartAlreadyException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HeartControllerAdvice {
    @ExceptionHandler(HeartAlreadyException.class)
    public ResponseEntity<String> heartAlreadyExceptionHandle(HeartAlreadyException heartAlreadyException) {
        return ResponseEntity.badRequest().body(heartAlreadyException.getMessage());
    }
}
