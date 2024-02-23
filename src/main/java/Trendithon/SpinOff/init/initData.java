package Trendithon.SpinOff.init;

import static Trendithon.SpinOff.domain.jobposting.entity.type.EmploymentType.REGULAR;
import static Trendithon.SpinOff.domain.jobposting.entity.type.ExperienceLevel.NEWCOMER;

import Trendithon.SpinOff.domain.board.dto.BoardDto;
import Trendithon.SpinOff.domain.board.entity.Board;
import Trendithon.SpinOff.domain.board.repository.BoardRepository;
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
    private final BoardRepository boardRepository;

    @PostConstruct
    public void init() throws JsonProcessingException {
        memberService.signUp(SignUpDto.builder()
                        .memberId("admin")
                        .name("tester")
                        .password("test1234")
                        .email("coy@naver.com")
                .build());
        memberService.signUp(SignUpDto.builder()
                .memberId("test1")
                .name("tester")
                .password("test1234")
                .email("coy@naver.com")
                .build());
        memberService.signUp(SignUpDto.builder()
                .memberId("test2")
                .name("tester")
                .password("test1234")
                .email("coy@naver.com")
                .build());
        memberService.signUp(SignUpDto.builder()
                .memberId("test3")
                .name("tester")
                .password("test1234")
                .email("coy@naver.com")
                .build());
        memberService.signUp(SignUpDto.builder()
                .memberId("khayoung")
                .email("coy@naver.com")
                .password("test1234")
                .name("권하영")
                .build());
        memberService.signUp(SignUpDto.builder()
                .memberId("doteeth")
                .email("coy@naver.com")
                .name("고도희")
                .password("test1234")
                .build());
        memberService.signUp(SignUpDto.builder()
                .memberId("pookey1104")
                .email("coy@naver.com")
                .name("김서윤")
                .password("test1234")
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

        boardRepository.save(Board.builder()
                        .projectImage("https://trendithonfile.s3.ap-northeast-2.amazonaws.com/ToTree.png")
                        .projectFeatures("나무 이쁘죠?")
                        .projectName("ToTree")
                        .distribution("https://ToTree.net")
                        .boardLike(9)
                        .projectDescription("크리스마스 트리가 이쁘네요")
                        .member1("test1")
                        .member2("test2")
                        .member3("test3")
                        .github("https://github.com")
                        .category("category")
                        .projectBackground("나무를 꾸며보자")
                        .writer("김서윤")
                .build());
        boardRepository.save(Board.builder()
                .projectImage("https://trendithonfile.s3.ap-northeast-2.amazonaws.com/Savior.png")
                .projectFeatures("여행")
                .projectName("Savior")
                .distribution("https://Savior.com")
                .boardLike(5)
                .projectDescription("싸게 가는 여행")
                .member1("test1")
                .member2("test2")
                .member3("test3")
                .github("https://github.com")
                .category("category")
                .projectBackground("여행 경비를 절약하자")
                .writer("김서윤")
                .build());
        boardRepository.save(Board.builder()
                .projectImage("https://trendithonfile.s3.ap-northeast-2.amazonaws.com/Proust.jpg")
                .projectFeatures("향수")
                .projectName("Proust")
                .distribution("https://Proust.org")
                .boardLike(13)
                .projectDescription("향수냄새 알고싶어?")
                .member1("test1")
                .member2("test2")
                .member3("test3")
                .github("https://github.com")
                .category("category")
                .projectBackground("향수 냄새 좋다 어떤거야?")
                .writer("김서윤")
                .build());
        boardRepository.save(Board.builder()
                .projectImage("https://trendithonfile.s3.ap-northeast-2.amazonaws.com/KakaoTalk_20240218_171344837+(1).png")
                .projectFeatures("옷")
                .projectName("chulsu's closet")
                .distribution("https://chulsuscloset.com")
                .boardLike(10)
                .projectDescription("옷빌려줘")
                .member1("test1")
                .member2("test2")
                .member3("test3")
                .github("https://github.com")
                .category("category")
                .projectBackground("옷이 없는데 돈도 없어")
                .writer("김서윤")
                .build());
        boardRepository.save(Board.builder()
                .projectImage("https://trendithonfile.s3.ap-northeast-2.amazonaws.com/%EB%9B%B0%EC%8A%88.png")
                .projectFeatures("건강/운동")
                .projectName("뛰슈")
                .distribution("https://main--runandrun.netlify.app/signin")
                .boardLike(10)
                .projectDescription("다같이 건강챙기면서 살자")
                .member1("test1")
                .member2("test2")
                .member3("test3")
                .github("https://github.com/LikeLionRunnersApp")
                .category("category")
                .projectBackground("건강챙기면서 공부하자는 뜻에서 시작한 프로젝트")
                .writer("박준태")
                .build());
        boardRepository.save(Board.builder()
                .projectImage("https://trendithonfile.s3.ap-northeast-2.amazonaws.com/%EB%8B%A4%EC%9A%B4%EB%A1%9C%EB%93%9C.png")
                .projectFeatures("축제 홍보")
                .projectName("상명대 축제")
                .distribution("http://상명대")
                .boardLike(0)
                .projectDescription("상명대 축제 홍보")
                .member1("test1")
                .member2("test2")
                .member3("test3")
                .github("https://github.com")
                .category("category")
                .projectBackground("축제 홍보")
                .writer("김민석")
                .build());
        boardRepository.save(Board.builder()
                .projectImage("https://trendithonfile.s3.ap-northeast-2.amazonaws.com/%EC%95%88%EB%8F%84.png")
                .projectFeatures("대중교통")
                .projectName("안도")
                .distribution("https://안도택시.net")
                .boardLike(4)
                .projectDescription("택시 합승")
                .member1("test1")
                .member2("test2")
                .member3("test3")
                .github("https://github.com/안도")
                .category("category")
                .projectBackground("택시 합승으로 안전하게 귀가하기")
                .writer("김서윤")
                .build());
        boardRepository.save(Board.builder()
                .projectImage("https://trendithonfile.s3.ap-northeast-2.amazonaws.com/%ED%8B%B0%EB%B0%8D.png")
                .projectFeatures("팀이름 작명")
                .projectName("티밍")
                .distribution("https://티밍.con")
                .boardLike(4)
                .projectDescription("팀이름 정해주기")
                .member1("test1")
                .member2("test2")
                .member3("test3")
                .github("https://github.com/티밍")
                .category("category")
                .projectBackground("팀프로젝트 이름을 정해줄게!")
                .writer("김서윤")
                .build());
    }

}