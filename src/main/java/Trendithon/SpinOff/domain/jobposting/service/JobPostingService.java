package Trendithon.SpinOff.domain.jobposting.service;

import Trendithon.SpinOff.domain.heart.entity.HeartJobPosting;
import Trendithon.SpinOff.domain.heart.repository.HeartJobPostingRepository;
import Trendithon.SpinOff.domain.jobposting.entity.JobPosting;
import Trendithon.SpinOff.domain.jobposting.entity.type.EmploymentType;
import Trendithon.SpinOff.domain.jobposting.entity.type.ExperienceLevel;
import Trendithon.SpinOff.domain.jobposting.repository.JobPostingJpaRepository;
import Trendithon.SpinOff.domain.jobposting.valid.exception.JobPostingNotFoundException;
import Trendithon.SpinOff.domain.member.entity.Member;
import Trendithon.SpinOff.domain.member.repository.MemberJpaRepository;
import Trendithon.SpinOff.domain.profile.valid.exception.MemberNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class JobPostingService {
    private final JobPostingJpaRepository jobPostingJpaRepository;
    private final HeartJobPostingRepository heartJobPostingRepository;
    private final MemberJpaRepository memberJpaRepository;

    public List<JobPosting> findByDeadlineAfterAndContain(LocalDateTime localDateTime, String jobTitle,
                                                          String companyName) {
        return jobPostingJpaRepository.findByDeadLineAfterAndJobTitleContainingOrCompanyNameContaining(
                localDateTime,
                jobTitle,
                companyName);
    }

    public List<JobPosting> findByDeadlineAfter(LocalDateTime localDateTime) {
        return jobPostingJpaRepository.findAllByDeadLineAfter(localDateTime);
    }

    public List<JobPosting> findByLikedJobPosting(String memberId) {
        Optional<Member> optionalMember = memberJpaRepository.findByMemberId(memberId);
        if (optionalMember.isPresent()) {
            Member member = optionalMember.get();
            Optional<List<HeartJobPosting>> optionalHeartJobPostings = heartJobPostingRepository.findByMember(member);
            if (optionalHeartJobPostings.isPresent()) {
                List<HeartJobPosting> heartJobPostings = optionalHeartJobPostings.get();
                return heartJobPostings.stream().map(HeartJobPosting::getJobPosting).collect(Collectors.toList());
            }
            throw new JobPostingNotFoundException("Member with memberId " + memberId + "not found");
        } else {
            throw new MemberNotFoundException("Member with memberId " + memberId + " not found");
        }
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
                .likeCount(0)
                .deadLine(deadLine)
                .level(level)
                .build();
        jobPostingJpaRepository.save(jobPosting);
        return true;
    }

    public JobPosting increaseViewCount(Long jobPostingId) {
        Optional<JobPosting> optionalJobPosting = jobPostingJpaRepository.findById(jobPostingId);
        if (optionalJobPosting.isPresent()) {
            JobPosting jobPosting = optionalJobPosting.get();
            jobPosting.increaseViewCount();
            return jobPostingJpaRepository.save(jobPosting);
        } else {
            throw new JobPostingNotFoundException("jobPosting with jobPostingId " + jobPostingId + " not found");
        }
    }

    public List<JobPosting> findPopularJobPostings() {
        return jobPostingJpaRepository.findPopularPosts();
    }
}
