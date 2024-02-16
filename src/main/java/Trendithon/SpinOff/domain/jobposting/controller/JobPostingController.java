package Trendithon.SpinOff.domain.jobposting.controller;

import Trendithon.SpinOff.domain.jobposting.dto.JobPostingDto;
import Trendithon.SpinOff.domain.jobposting.dto.SearchMessage;
import Trendithon.SpinOff.domain.jobposting.entity.JobPosting;
import Trendithon.SpinOff.domain.jobposting.entity.type.EmploymentType;
import Trendithon.SpinOff.domain.jobposting.entity.type.ExperienceLevel;
import Trendithon.SpinOff.domain.jobposting.service.JobPostingService;
import java.time.LocalDateTime;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api")
public class JobPostingController {
    private final JobPostingService jobPostingService;

    public JobPostingController(JobPostingService jobPostingService) {
        this.jobPostingService = jobPostingService;
    }

    @GetMapping("/jobs/like")
    public ResponseEntity<List<JobPosting>> getLikeJobPostings() {
        return ResponseEntity.ok().build();
    }   // 좋아요 기능에 따른 구현 필요

    @GetMapping("/jobs")
    public ResponseEntity<List<JobPosting>> getJobPostings(@RequestBody SearchMessage searchMessage) {
        String companyName = searchMessage.companyName();
        String jobTitle = searchMessage.jobTitle();
        LocalDateTime localDateTime = searchMessage.now();
        return ResponseEntity.ok()
                .body(jobPostingService.findByDeadlineAfterAndContain(localDateTime, jobTitle, companyName));
    }

    @PostMapping("/jobPosting")
    public ResponseEntity<Boolean> addJobPosting(@RequestBody JobPostingDto jobPostingDto) {
        String logoUrl = jobPostingDto.logoUrl();
        String companyName = jobPostingDto.companyName();
        Integer viewCount = jobPostingDto.viewCount();
        Integer applicantsCount = jobPostingDto.applicantsCount();
        String jobTitle = jobPostingDto.jobTitle();
        EmploymentType type = jobPostingDto.type();
        LocalDateTime deadLine = jobPostingDto.deadLine();
        ExperienceLevel level = jobPostingDto.level();
        return ResponseEntity.ok().body(jobPostingService.savePosting(
                logoUrl,
                companyName,
                viewCount,
                applicantsCount,
                jobTitle,
                type,
                deadLine,
                level
        ));
    }

    @PostMapping("/view/increase/{jobPostingId}")
    public ResponseEntity<Boolean> increaseViewCount(@PathVariable Long jobPostingId) {
        log.info("jobPostingId = {}", jobPostingId);
        return ResponseEntity.ok().body(jobPostingService.increaseViewCount(jobPostingId));
    }
}
