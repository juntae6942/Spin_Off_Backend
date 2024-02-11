package Trendithon.SpinOff.domain.member.repository;

import Trendithon.SpinOff.domain.member.entity.ProfileTechnic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileTechnicJpaRepository extends JpaRepository<ProfileTechnic, Long> {}
