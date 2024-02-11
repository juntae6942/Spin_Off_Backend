package Trendithon.SpinOff.domain.member.service;

import Trendithon.SpinOff.domain.member.dto.EditInformation;
import Trendithon.SpinOff.domain.member.dto.Information;
import Trendithon.SpinOff.domain.member.entity.Member;
import Trendithon.SpinOff.domain.member.entity.Profile;
import Trendithon.SpinOff.domain.member.entity.ProfileTechnic;
import Trendithon.SpinOff.domain.member.entity.Technic;
import Trendithon.SpinOff.domain.member.repository.MemberJpaRepository;
import Trendithon.SpinOff.domain.member.repository.ProfileJpaRepository;
import Trendithon.SpinOff.domain.member.repository.ProfileTechnicJpaRepository;
import Trendithon.SpinOff.domain.member.repository.TechnicJpaRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class ProfileService {
    private final MemberJpaRepository memberJpaRepository;
    private final TechnicJpaRepository technicJpaRepository;
    private final ProfileJpaRepository profileJpaRepository;
    private final ProfileTechnicJpaRepository profileTechnicJpaRepository;

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
}
