package Trendithon.SpinOff.domain.member.valid;

import Trendithon.SpinOff.domain.member.valid.exception.IntroduceOutOfBoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ProfileControllerAdvice {
    @ExceptionHandler(IntroduceOutOfBoundException.class)
    public ResponseEntity<String> introduceOutOfBoundsExceptionHandle(IntroduceOutOfBoundException introduceOutOfBoundException) {
        log.error("error message = {}", introduceOutOfBoundException.getMessage());
        return ResponseEntity.badRequest().body(introduceOutOfBoundException.getMessage());
    }
}
