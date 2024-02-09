package Trendithon.SpinOff.domain.member.service;

import Trendithon.SpinOff.global.jwt.entity.Authority;
import Trendithon.SpinOff.domain.member.entity.Member;
import Trendithon.SpinOff.domain.member.entity.Profile;
import Trendithon.SpinOff.domain.member.entity.Technic;
import Trendithon.SpinOff.domain.member.repository.AuthorityJpaRepository;
import Trendithon.SpinOff.domain.member.repository.MemberJpaRepository;
import Trendithon.SpinOff.domain.member.dto.SignUpDto;
import Trendithon.SpinOff.domain.member.repository.ProfileJpaRepository;
import Trendithon.SpinOff.domain.member.repository.TechnicJpaRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class MemberService {
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    private static final String PHONE_PATTERN = "^(02|0[1-9][0-9]?)-[0-9]{3,4}-[0-9]{4}$";
    private static final Pattern phonePattern = Pattern.compile(PHONE_PATTERN);

    private final MemberJpaRepository memberJpaRepository;//멤버 저장소
    private final PasswordEncoder passwordEncoder;
    private final AuthorityJpaRepository authorityJpaRepository;
    private final TechnicJpaRepository technicJpaRepository;
    private final ProfileJpaRepository profileJpaRepository;

    @Transactional
    public ResponseEntity<Boolean> signUp(SignUpDto memberDto) {
        if(memberJpaRepository.findByMemberId(memberDto.getMemberId()).orElse(null)!=null) {
            throw new RuntimeException("이미 가입되어 있는 유저입니다.");
        }
        Authority authority = authorityJpaRepository.findByAuthority("ROLE_USER")
                .orElseGet(() -> Authority.builder()
                .authority("ROLE_USER")
                .build());

        Profile profile = Profile.builder().technics(new HashSet<>()).build();
        Member member = Member.builder()
                .memberId(memberDto.getMemberId())
                .email(memberDto.getEmail())
                .name(memberDto.getName())
                .authority(authority)
                .profile(profile)
                .activate(true)
                .password(passwordEncoder.encode(memberDto.getPassword()))
                .build();

        log.info(member.getEmail());


        Member save = memberJpaRepository.save(member);

        log.info("멤버 저장 됨 {}",save.getId());
        log.info(save.getPassword());

        return ResponseEntity.ok(true);
    }

    public boolean addTechnic(String memberId, Set<String> technics) {
        log.info("멤버 아이디 = {}", memberId);
        log.info("기술 스택 = {}", technics);
        Optional<Member> optionalMember = memberJpaRepository.findByMemberId(memberId);
        if (optionalMember.isPresent()) {
           Member member = optionalMember.get();
            technics.stream().filter(technic -> technicJpaRepository.findByTechnicName(technic).isEmpty())
                    .forEach(technic -> technicJpaRepository.save(Technic.builder()
                    .technicName(technic).build()));
            Set<Technic> existTechnics = member.getProfile().getTechnics();
            Set<Technic> newTechnics = technics.stream()
                    .map(technicName -> technicJpaRepository.findByTechnicName(technicName)
                            .orElseGet(() -> Technic.builder().technicName(technicName).build()))
                    .collect(Collectors.toSet());
            for (Technic newTechnic : newTechnics) {
                if (!existTechnics.contains(newTechnic)) {
                   member.getProfile().getTechnics().add(newTechnic);
                }

            }

            memberJpaRepository.save(member);
        } else {
            log.error("Member NotFound");
            return false;
        }
        return true;
    }

    public Optional<Member> findById(Long id) {
        return memberJpaRepository.findMemberById(id);
    }

    public Optional<Member> findByName(String name) {
        return memberJpaRepository.findMemberByName(name);
    }

    public Optional<Member> findByEmail(String email) {
        return memberJpaRepository.findByEmail(email);
    }

    public Optional<Member> findByMemberId(String memberId) {
        return memberJpaRepository.findByMemberId(memberId);
    }

    public boolean isValidEmail(String email) {
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean isValidPhone(String phone) {
        Matcher matcher = phonePattern.matcher(phone);
        return matcher.matches();
    }
}