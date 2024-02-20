package Trendithon.SpinOff.domain.jobposting.entity;

import Trendithon.SpinOff.domain.jobposting.entity.type.EmploymentType;
import Trendithon.SpinOff.domain.jobposting.entity.type.ExperienceLevel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class JobPosting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String logoUrl;
    private String companyName;
    private Integer viewCount;
    private Integer applicantsCount;
    private String jobTitle;
    private EmploymentType type;
    private LocalDateTime deadLine;
    private ExperienceLevel level;
    private Integer likeCount;

    public void increaseViewCount() {
        viewCount++;
    }

    public void increaseLikeCount() {
        likeCount++;
    }

    public void decreaseLikeCount() {
        --likeCount;
    }
}
