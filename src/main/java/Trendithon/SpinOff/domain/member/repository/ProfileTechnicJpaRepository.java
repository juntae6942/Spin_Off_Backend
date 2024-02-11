package Trendithon.SpinOff.domain.member.repository;

import Trendithon.SpinOff.domain.member.entity.ProfileTechnic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileTechnicJpaRepository extends JpaRepository<ProfileTechnic, Long> {
    @Override
    Optional<ProfileTechnic> findById(Long id);
}
