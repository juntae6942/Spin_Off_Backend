package Trendithon.SpinOff.domain.heart.valid.exception;

import java.util.NoSuchElementException;

public class HeartJobPostingNotFoundException extends NoSuchElementException {
    public HeartJobPostingNotFoundException(String message) {
        super(message);
    }
}
