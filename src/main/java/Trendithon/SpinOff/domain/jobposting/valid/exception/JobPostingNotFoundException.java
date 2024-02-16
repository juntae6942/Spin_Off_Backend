package Trendithon.SpinOff.domain.jobposting.valid.exception;

import java.util.NoSuchElementException;

public class JobPostingNotFoundException extends NoSuchElementException {
    public JobPostingNotFoundException(String message) {
        super(message);
    }
}
