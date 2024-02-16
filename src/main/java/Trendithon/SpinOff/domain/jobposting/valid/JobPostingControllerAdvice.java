package Trendithon.SpinOff.domain.jobposting.valid;

import Trendithon.SpinOff.domain.jobposting.valid.exception.JobPostingNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class JobPostingControllerAdvice {
    @ExceptionHandler(JobPostingNotFoundException.class)
    public ResponseEntity<String> jobPostingNotFoundExceptionHandle(
            JobPostingNotFoundException jobPostingNotFoundException) {
        return ResponseEntity.badRequest().body(jobPostingNotFoundException.getMessage());
    }
}
