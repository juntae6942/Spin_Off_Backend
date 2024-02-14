package Trendithon.SpinOff.domain.member.valid;

import Trendithon.SpinOff.domain.member.valid.exception.CheckMemberIdNotNullException;
import Trendithon.SpinOff.domain.member.valid.exception.IntroduceOutOfBoundException;
import Trendithon.SpinOff.domain.member.valid.exception.MemberNotFoundException;
import Trendithon.SpinOff.domain.member.valid.exception.ProfileNotFoundException;
import io.micrometer.core.instrument.search.MeterNotFoundException;
import jakarta.persistence.EntityNotFoundException;
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

    @ExceptionHandler(CheckMemberIdNotNullException.class)
    public ResponseEntity<String> checkMemberIdNotNullExceptionHandle(CheckMemberIdNotNullException checkMemberIdNotNullException) {
        log.error("error message = {}", checkMemberIdNotNullException.getMessage());
        return ResponseEntity.badRequest().body(checkMemberIdNotNullException.getMessage());
    }

    @ExceptionHandler({ProfileNotFoundException.class, MemberNotFoundException.class})
    public ResponseEntity<String> profileNotFoundExceptionHandle(EntityNotFoundException notFoundException) {
        log.error("error message = {}", notFoundException.getMessage());
        return ResponseEntity.badRequest().body(notFoundException.getMessage());
    }
}
