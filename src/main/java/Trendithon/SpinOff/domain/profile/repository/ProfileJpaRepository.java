package Trendithon.SpinOff.domain.profile.repository;

import Trendithon.SpinOff.domain.profile.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileJpaRepository extends JpaRepository<Profile, Long> {
}
