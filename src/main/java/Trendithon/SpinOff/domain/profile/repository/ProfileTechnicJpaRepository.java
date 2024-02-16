package Trendithon.SpinOff.domain.profile.repository;

import Trendithon.SpinOff.domain.profile.entity.ProfileTechnic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface ProfileTechnicJpaRepository extends JpaRepository<ProfileTechnic, Long> {
    void deleteAllByProfileId(Long profileId);

    Optional<Set<ProfileTechnic>> findAllByProfileId(Long profileId);
}
