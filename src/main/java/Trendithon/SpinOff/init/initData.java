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
                .logoUrl("https://s3.ap-northeast-2.amazonaws.com/trendithon.enterprise.logo/%EA%B5%90%EB%B3%B4%EC%83%9D%EB%AA%85%EB%B3%B4%ED%97%98.jpg")
                .companyName("교보생명보험")
                .viewCount(0)
                .applicantsCount(10)
                .jobTitle("백엔드 채용 공고")
                .type(REGULAR)
                .likeCount(0)
                .deadLine(LocalDateTime.of(2024,7,3,10,10))
                .level(NEWCOMER)
                .build();
        jobPostingJpaRepository.save(jobPosting);
        JobPosting jobPosting1 = JobPosting.builder()
                .logoUrl("https://s3.ap-northeast-2.amazonaws.com/trendithon.enterprise.logo/%EA%B8%88%ED%98%B8%EC%84%9D%EC%9C%A0%ED%99%94%ED%95%99.jpg")
                .companyName("금호석유화학")
                .viewCount(0)
                .applicantsCount(10)
                .jobTitle("프런트 채용 공고")
                .type(REGULAR)
                .likeCount(0)
                .deadLine(LocalDateTime.of(2024,8,3,10,10))
                .level(NEWCOMER)
                .build();
        jobPostingJpaRepository.save(jobPosting1);
        JobPosting jobPosting2 = JobPosting.builder()
                .logoUrl("https://s3.ap-northeast-2.amazonaws.com/trendithon.enterprise.logo/%EA%B8%88%ED%98%B8%EC%95%84%EC%8B%9C%EC%95%84%EB%82%98.png")
                .companyName("금호아시아나")
                .viewCount(0)
                .applicantsCount(10)
                .jobTitle("기획자 채용 공고")
                .type(REGULAR)
                .likeCount(0)
                .deadLine(LocalDateTime.of(2024,9,3,10,10))
                .level(NEWCOMER)
                .build();
        jobPostingJpaRepository.save(jobPosting2);
        JobPosting jobPosting3 = JobPosting.builder()
                .logoUrl("https://s3.ap-northeast-2.amazonaws.com/trendithon.enterprise.logo/%EB%84%A4%EC%9D%B4%EB%B2%84.png")
                .companyName("네이버")
                .viewCount(0)
                .applicantsCount(10)
                .jobTitle("AI데이터분석 엔지니어")
                .type(REGULAR)
                .likeCount(0)
                .deadLine(LocalDateTime.of(2024,10,3,10,10))
                .level(NEWCOMER)
                .build();
        jobPostingJpaRepository.save(jobPosting3);
        JobPosting jobPosting4 = JobPosting.builder()
                .logoUrl("https://s3.ap-northeast-2.amazonaws.com/trendithon.enterprise.logo/%EB%84%A5%EC%8A%A8.png")
                .companyName("넥슨")
                .viewCount(0)
                .applicantsCount(10)
                .jobTitle("프로그램 엔지니어")
                .type(REGULAR)
                .likeCount(0)
                .deadLine(LocalDateTime.of(2024,5,3,10,10))
                .level(NEWCOMER)
                .build();
        jobPostingJpaRepository.save(jobPosting4);
        JobPosting jobPosting5 = JobPosting.builder()
                .logoUrl("https://s3.ap-northeast-2.amazonaws.com/trendithon.enterprise.logo/%EB%84%B7%EB%A7%88%EB%B8%94.jpg")
                .companyName("넷마블")
                .viewCount(0)
                .applicantsCount(10)
                .jobTitle("UI디자이너 채용")
                .type(REGULAR)
                .likeCount(0)
                .deadLine(LocalDateTime.of(2024,4,3,10,10))
                .level(NEWCOMER)
                .build();
        jobPostingJpaRepository.save(jobPosting5);
        JobPosting jobPosting6 = JobPosting.builder()
                .logoUrl("https://s3.ap-northeast-2.amazonaws.com/trendithon.enterprise.logo/%EB%86%8D%ED%98%91.png")
                .companyName("농협")
                .viewCount(0)
                .applicantsCount(10)
                .jobTitle("데이터분석 엔지니어")
                .type(REGULAR)
                .likeCount(0)
                .deadLine(LocalDateTime.of(2024,3,3,10,10))
                .level(NEWCOMER)
                .build();
        jobPostingJpaRepository.save(jobPosting6);
        JobPosting jobPosting7 = JobPosting.builder()
                .logoUrl("https://s3.ap-northeast-2.amazonaws.com/trendithon.enterprise.logo/%EB%8C%80%EB%A6%BC.jpg")
                .companyName("대림")
                .viewCount(0)
                .applicantsCount(10)
                .jobTitle("ML엔지니어 채용")
                .type(REGULAR)
                .likeCount(0)
                .deadLine(LocalDateTime.of(2024,2,3,10,10))
                .level(NEWCOMER)
                .build();
        jobPostingJpaRepository.save(jobPosting7);
        JobPosting jobPosting8 = JobPosting.builder()
                .logoUrl("https://s3.ap-northeast-2.amazonaws.com/trendithon.enterprise.logo/%EB%84%A4%EC%9D%B4%EB%B2%84.png")
                .companyName("네이버")
                .viewCount(0)
                .applicantsCount(10)
                .jobTitle("결제시스템관리 백엔드")
                .type(REGULAR)
                .likeCount(0)
                .deadLine(LocalDateTime.of(2024,4,3,10,10))
                .level(NEWCOMER)
                .build();
        jobPostingJpaRepository.save(jobPosting8);

    }

}