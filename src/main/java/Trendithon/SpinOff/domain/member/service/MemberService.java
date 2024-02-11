package Trendithon.SpinOff.domain.member.service;

import Trendithon.SpinOff.domain.member.dto.EditInformation;
import Trendithon.SpinOff.domain.member.dto.Information;
import Trendithon.SpinOff.domain.member.entity.ProfileTechnic;
import Trendithon.SpinOff.domain.member.repository.*;
import Trendithon.SpinOff.global.jwt.entity.Authority;
import Trendithon.SpinOff.domain.member.entity.Member;
import Trendithon.SpinOff.domain.member.entity.Profile;
import Trendithon.SpinOff.domain.member.entity.Technic;
import Trendithon.SpinOff.domain.member.dto.SignUpDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
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
    private final ProfileTechnicJpaRepository profileTechnicJpaRepository;

    @Transactional
    public ResponseEntity<Boolean> signUp(SignUpDto memberDto) {
        if(memberJpaRepository.findByMemberId(memberDto.getMemberId()).orElse(null)!=null) {
            throw new RuntimeException("이미 가입되어 있는 유저입니다.");
        }
        Authority authority = authorityJpaRepository.findByAuthority("ROLE_USER")
                .orElseGet(() -> Authority.builder()
                .authority("ROLE_USER")
                .build());

        Profile profile = Profile.builder()
                .profileTechnic(ProfileTechnic.builder()
                        .technics(new HashSet<>())
                        .build())
                .build();

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
        Optional<Member> optionalMember = memberJpaRepository.findByMemberId(memberId);
        if (optionalMember.isPresent()) {
            Member member = optionalMember.get();
            Profile profile = member.getProfile();

            Set<Technic> collects = technics.stream()
                    .filter(technic -> technicJpaRepository.findByTechnicName(technic).isEmpty())
                    .map(technic -> technicJpaRepository.save(Technic.builder()
                            .technicName(technic)
                            .build()))
                    .collect(Collectors.toSet());

            profile.getProfileTechnic().setTechnics(collects);
            profileTechnicJpaRepository.save(profile.getProfileTechnic());
            profileJpaRepository.save(profile);
            return true;
        } else {
            log.error("Member NotFound");
            return false;
        }
    }

    public boolean editTechnics(EditInformation editInformation) {
        Optional<Profile> optionalProfile = profileJpaRepository.findById(editInformation.getProfileId());
        if (optionalProfile.isEmpty()) {
            return false;
        }

        Profile profile = optionalProfile.get();
        ProfileTechnic profileTechnic = profile.getProfileTechnic();
        if (profileTechnic == null) {
            return false;
        }

        Set<Technic> newTechnics = editInformation.getTechnics();
        log.info("new {}", newTechnics);

        for (Technic newTechnic : newTechnics) {
            if(technicJpaRepository.findByTechnicName(newTechnic.getTechnicName()).isEmpty()) {
                technicJpaRepository.save(newTechnic);
            }
        }

        Set<Technic> collect = getTechnics(newTechnics);
        log.info("collect = {}", collect);

        profileTechnic.setTechnics(collect);
        profileTechnicJpaRepository.save(profileTechnic);
        profileJpaRepository.save(profile);
        return true;
    }

    private Set<Technic> getTechnics(Set<Technic> newTechnics) {
        return newTechnics.stream().map(technic -> technicJpaRepository
                .findByTechnicName(technic.getTechnicName())
                .orElseGet(() -> Technic.builder()
                        .technicName(technic.getTechnicName())
                        .build()
                )).collect(Collectors.toSet());
    }

    public boolean addInformation(Information information) {
        Optional<Member> member = memberJpaRepository.findByMemberId(information.getMemberId());
        if(member.isPresent()) {
            Profile profile = member.get().getProfile();
            profile.add(information);
            profileJpaRepository.save(profile);
            return true;
        }
        return false;
    }

    public boolean editInformation(EditInformation editInformation) {
        Optional<Member> member = memberJpaRepository.findByMemberId(editInformation.getMemberId());
        if (member.isPresent()) {
            Profile profile = member.get().getProfile();
            return profile.edit(editInformation);
        }
        return false;
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