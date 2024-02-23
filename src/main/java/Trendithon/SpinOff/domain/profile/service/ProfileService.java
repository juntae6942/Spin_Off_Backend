package Trendithon.SpinOff.domain.profile.service;

import Trendithon.SpinOff.domain.board.entity.Board;
import Trendithon.SpinOff.domain.board.repository.BoardRepository;
import Trendithon.SpinOff.domain.member.dto.EditInformation;
import Trendithon.SpinOff.domain.member.dto.Information;
import Trendithon.SpinOff.domain.member.dto.ProfileInformation;
import Trendithon.SpinOff.domain.member.entity.Member;
import Trendithon.SpinOff.domain.profile.entity.Profile;
import Trendithon.SpinOff.domain.profile.entity.ProfileTechnic;
import Trendithon.SpinOff.domain.profile.entity.Technic;
import Trendithon.SpinOff.domain.member.repository.MemberJpaRepository;
import Trendithon.SpinOff.domain.profile.repository.ProfileJpaRepository;
import Trendithon.SpinOff.domain.profile.repository.ProfileTechnicJpaRepository;
import Trendithon.SpinOff.domain.profile.repository.TechnicJpaRepository;
import Trendithon.SpinOff.domain.profile.valid.exception.MemberNotFoundException;
import Trendithon.SpinOff.domain.profile.valid.exception.ProfileNotFoundException;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
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
    private final BoardRepository boardRepository;

    public boolean addTechnic(String memberId, Set<String> technics) {
        Optional<Member> optionalMember = memberJpaRepository.findByMemberId(memberId);
        if (optionalMember.isPresent()) {
            Member member = optionalMember.get();
            Profile profile = member.getProfile();
            // 새로운 기술을 추가하거나 이미 있는 기술을 업데이트합니다.
            saveTechnicForEach(technics, profile);
            return true;
        } else {
            throw new MemberNotFoundException("Member with memberId " + memberId + " not found");
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
            throw new MemberNotFoundException("Member with memberId " + memberId + " not found");
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
        if (member.isPresent()) {
            Profile profile = member.get().getProfile();
            profile.add(information);
            profileJpaRepository.save(profile);
            return true;
        } else {
            throw new MemberNotFoundException("Member with memberId " + information.getMemberId() + " not found");
        }
    }

    public boolean editInformation(EditInformation editInformation) {
        Optional<Member> member = memberJpaRepository.findByMemberId(editInformation.getMemberId());
        if (member.isPresent()) {
            Profile profile = member.get().getProfile();
            return profile.edit(editInformation);
        } else {
            throw new MemberNotFoundException("Member with memberId " + editInformation.getMemberId() + " not found");
        }
    }

    public ProfileInformation checkInformation(String memberId) {
        Optional<Member> optionalMember = memberJpaRepository.findByMemberId(memberId);
        if (optionalMember.isPresent()) {
            Member member = optionalMember.get();
            Profile profile = member.getProfile();
            ProfileInformation information = profile.toDto();
            Optional<Set<ProfileTechnic>> technics = profileTechnicJpaRepository.findAllByProfileId(profile.getId());
            setTechnics(technics, information);
            information.setName(member.getName());
            List<Board> boards = boardRepository.findAllByWriter(memberId);
            information.setBoards(boards);
            return information;
        } else {
            throw new MemberNotFoundException("memberId with " + memberId + " not found");
        }
    }

    private static void setTechnics(Optional<Set<ProfileTechnic>> technics, ProfileInformation information) {
        if (technics.isPresent()) {
            Set<String> result = new HashSet<>();
            Set<ProfileTechnic> profileTechnics = technics.get();
            for (ProfileTechnic profileTechnic : profileTechnics) {
                result.add(profileTechnic.getTechnic().getTechnicName());
            }
            information.setTechnics(result);
        } else {
            throw new ProfileNotFoundException("memberId with " + information.getName() + " not found");
        }
    }

    public String checkProfileImage(String memberId) {
        Member member = memberJpaRepository.findByMemberId(memberId)
                .orElseThrow(() -> new MemberNotFoundException("memberId with " + memberId + " not found"));
        return member.getProfile().getImageUrl();
    }
}
