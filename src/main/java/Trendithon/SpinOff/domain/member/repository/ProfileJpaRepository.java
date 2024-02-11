package Trendithon.SpinOff.domain.member.repository;

import Trendithon.SpinOff.domain.member.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileJpaRepository extends JpaRepository<Profile, Long> {
    Optional<Profile> findById(Long id);
}
