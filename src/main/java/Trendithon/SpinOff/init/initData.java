package Trendithon.SpinOff.init;

import static Trendithon.SpinOff.domain.jobposting.entity.type.EmploymentType.REGULAR;
import static Trendithon.SpinOff.domain.jobposting.entity.type.ExperienceLevel.NEWCOMER;

import Trendithon.SpinOff.domain.board.dto.BoardDto;
import Trendithon.SpinOff.domain.board.entity.Board;
import Trendithon.SpinOff.domain.board.service.BoardService;
import Trendithon.SpinOff.domain.jobposting.entity.JobPosting;
import Trendithon.SpinOff.domain.jobposting.repository.JobPostingJpaRepository;
import Trendithon.SpinOff.domain.jobposting.service.JobPostingService;
import Trendithon.SpinOff.domain.member.dto.SignUpDto;
import Trendithon.SpinOff.domain.member.service.MemberService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.annotation.PostConstruct;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class initData {
    private final MemberService memberService;
    private final BoardService boardService;
    private final JobPostingService jobPostingService;
    private final JobPostingJpaRepository jobPostingJpaRepository;
    @PostConstruct
    public void init() throws JsonProcessingException {
        memberService.signUp(SignUpDto.builder()
                        .memberId("admin")
                        .name("tester")
                        .password("test123")
                        .email("coy@naver.com")
                .build());
        memberService.signUp(SignUpDto.builder()
                .memberId("test1")
                .name("tester")
                .password("test123")
                .email("coy@naver.com")
                .build());
        memberService.signUp(SignUpDto.builder()
                .memberId("test2")
                .name("tester")
                .password("test123")
                .email("coy@naver.com")
                .build());
        memberService.signUp(SignUpDto.builder()
                .memberId("test3")
                .name("tester")
                .password("test123")
                .email("coy@naver.com")
                .build());
        JobPosting jobPosting = JobPosting.builder()
                .logoUrl("https://abcdef:8080")
                .companyName("coy")
                .viewCount(0)
                .applicantsCount(10)
                .jobTitle("코이물산")
                .type(REGULAR)
                .likeCount(0)
                .deadLine(LocalDateTime.of(2024,1,3,10,10))
                .level(NEWCOMER)
                .build();
        jobPostingJpaRepository.save(jobPosting);
        JobPosting jobPosting1 = JobPosting.builder()
                .logoUrl("https://abcdef:8080")
                .companyName("coy123")
                .viewCount(0)
                .applicantsCount(10)
                .jobTitle("코이아카데미")
                .type(REGULAR)
                .likeCount(0)
                .deadLine(LocalDateTime.of(2024,1,3,10,10))
                .level(NEWCOMER)
                .build();
        jobPostingJpaRepository.save(jobPosting1);
        JobPosting jobPosting2 = JobPosting.builder()
                .logoUrl("https://abcdef:8080")
                .companyName("coy123")
                .viewCount(0)
                .applicantsCount(10)
                .jobTitle("코이바이오")
                .type(REGULAR)
                .likeCount(0)
                .deadLine(LocalDateTime.of(2024,1,3,10,10))
                .level(NEWCOMER)
                .build();
        jobPostingJpaRepository.save(jobPosting2);
    }
}