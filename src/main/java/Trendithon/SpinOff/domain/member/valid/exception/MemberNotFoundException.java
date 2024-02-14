package Trendithon.SpinOff.domain.member.valid.exception;

import jakarta.persistence.EntityNotFoundException;

public class MemberNotFoundException extends EntityNotFoundException {
    public MemberNotFoundException(String message) {
        super(message);
    }
}
