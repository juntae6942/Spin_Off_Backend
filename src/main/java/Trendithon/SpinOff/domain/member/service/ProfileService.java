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
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

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
                // 새로운 기술을 추가하거나 이미 있는 기술을 업데이트합니다.
                saveTechnicForEach(technics, profile);
                return true;
            } else {
                    throw new EntityNotFoundException("Member with memberId " + memberId + " not found");
            }
    }

    @Transactional
    public boolean editTechnics(String memberId, Set<String> newTechnics) {
        Optional<Member> optionalMember = memberJpaRepository.findByMemberId(memberId);
        if (optionalMember.isPresent()) {
            Member member = optionalMember.get();
            Profile profile = member.getProfile();
            profileTechnicJpaRepository.deleteAllByProfileId(profile.getId());
            return saveTechnicForEach(newTechnics, profile);
        } else {
            throw new EntityNotFoundException("Member with memberId " + memberId + " not found");
        }
    }

    private boolean saveTechnicForEach(Set<String> newTechnics, Profile profile) {
        for (String technicName : newTechnics) {
            Technic technic;
            technic = saveTechnic(technicName);
            ProfileTechnic profileTechnic = new ProfileTechnic();
            profileTechnic.setProfile(profile);
            profileTechnic.setTechnic(technic);
            profileTechnicJpaRepository.save(profileTechnic);
        }
        return true;
    }

    private Technic saveTechnic(String technicName) {
        Technic technic;
        if (technicJpaRepository.existsByTechnicName(technicName)) {
            technic = getTechnic(technicName);
        } else {
            technic = new Technic();
            technic.setTechnicName(technicName);
            // 다른 필요한 속성들을 설정합니다.
            technic = technicJpaRepository.save(technic);
        }
        return technic;
    }

    private Technic getTechnic(String technicName) {
        Optional<Technic> optionalTechnic = technicJpaRepository.findById(technicName);
        return optionalTechnic.orElse(null);
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
