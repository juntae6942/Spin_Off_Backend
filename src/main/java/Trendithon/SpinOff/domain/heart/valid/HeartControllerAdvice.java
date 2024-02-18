package Trendithon.SpinOff.domain.heart.valid;

import Trendithon.SpinOff.domain.heart.valid.exception.HeartAlreadyException;
import Trendithon.SpinOff.domain.heart.valid.exception.HeartJobPostingNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HeartControllerAdvice {
    @ExceptionHandler(HeartAlreadyException.class)
    public ResponseEntity<String> heartAlreadyExceptionHandle(HeartAlreadyException heartAlreadyException) {
        return ResponseEntity.badRequest().body(heartAlreadyException.getMessage());
    }

    @ExceptionHandler(HeartJobPostingNotFoundException.class)
    public ResponseEntity<String> heartJobPostingNotFoundExceptionHandle(
            HeartJobPostingNotFoundException heartJobPostingNotFoundException) {
        return ResponseEntity.badRequest().body(heartJobPostingNotFoundException.getMessage());
    }
}
