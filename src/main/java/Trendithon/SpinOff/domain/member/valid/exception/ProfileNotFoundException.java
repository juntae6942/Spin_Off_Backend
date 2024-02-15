package Trendithon.SpinOff.domain.member.valid.exception;

import jakarta.persistence.EntityNotFoundException;

public class ProfileNotFoundException extends EntityNotFoundException {
    public ProfileNotFoundException(String message) {
        super(message);
    }
}
