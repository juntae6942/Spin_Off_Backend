package Trendithon.SpinOff.domain.jobposting.service;

import Trendithon.SpinOff.domain.jobposting.entity.JobPosting;
import Trendithon.SpinOff.domain.jobposting.entity.type.EmploymentType;
import Trendithon.SpinOff.domain.jobposting.entity.type.ExperienceLevel;
import Trendithon.SpinOff.domain.jobposting.repository.JobPostingJpaRepository;
import Trendithon.SpinOff.domain.jobposting.valid.exception.JobPostingNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class JobPostingService {
    private final JobPostingJpaRepository jobPostingJpaRepository;

    public JobPostingService(JobPostingJpaRepository jobPostingJpaRepository) {
        this.jobPostingJpaRepository = jobPostingJpaRepository;
    }

    public List<JobPosting> findByDeadlineAfterAndContain(LocalDateTime localDateTime, String jobTitle,
                                                          String companyName) {
        return jobPostingJpaRepository.findByDeadLineAfterAndJobTitleContainingOrCompanyNameContaining(
                localDateTime,
                jobTitle,
                companyName);
    }

    public boolean savePosting(String logoUrl, String companyName, Integer viewCount, Integer applicantsCount,
                               String jobTitle, EmploymentType type, LocalDateTime deadLine, ExperienceLevel level) {
        JobPosting jobPosting = JobPosting.builder()
                .logoUrl(logoUrl)
                .companyName(companyName)
                .viewCount(viewCount)
                .applicantsCount(applicantsCount)
                .jobTitle(jobTitle)
                .type(type)
                .deadLine(deadLine)
                .level(level)
                .build();
        jobPostingJpaRepository.save(jobPosting);
        return true;
    }

    public boolean increaseViewCount(Long jobPostingId) {
        Optional<JobPosting> optionalJobPosting = jobPostingJpaRepository.findById(jobPostingId);
        if (optionalJobPosting.isPresent()) {
            JobPosting jobPosting = optionalJobPosting.get();
            jobPosting.increaseViewCount();
            jobPostingJpaRepository.save(jobPosting);
            return true;
        } else {
            throw new JobPostingNotFoundException("jobPosting with jobPostingId " + jobPostingId + " not found");
        }
    }
}
