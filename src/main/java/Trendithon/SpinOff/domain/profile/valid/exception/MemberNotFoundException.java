package Trendithon.SpinOff.domain.profile.valid.exception;

import jakarta.persistence.EntityNotFoundException;

public class MemberNotFoundException extends EntityNotFoundException {
    public MemberNotFoundException(String message) {
        super(message);
    }
}
