package Trendithon.SpinOff.domain.heart.valid.exception;

import org.springframework.dao.DuplicateKeyException;

public class HeartAlreadyException extends DuplicateKeyException {
    public HeartAlreadyException(String message) {
        super(message);
    }
}
