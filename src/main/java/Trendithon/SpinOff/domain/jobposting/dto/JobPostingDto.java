package Trendithon.SpinOff.domain.jobposting.dto;

import Trendithon.SpinOff.domain.jobposting.entity.type.EmploymentType;
import Trendithon.SpinOff.domain.jobposting.entity.type.ExperienceLevel;
import java.time.LocalDateTime;

public record JobPostingDto(
        String logoUrl,
        String companyName,
        Integer viewCount,
        Integer applicantsCount,
        String jobTitle,
        EmploymentType type,
        LocalDateTime deadLine,
        ExperienceLevel level
) {
}
