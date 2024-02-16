package Trendithon.SpinOff.domain.jobposting.dto;

import java.time.LocalDateTime;

public record SearchMessage(
        LocalDateTime now,
        String companyName,
        String jobTitle
) {

}
