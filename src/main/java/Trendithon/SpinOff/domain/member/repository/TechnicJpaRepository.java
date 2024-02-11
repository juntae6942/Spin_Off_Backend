package Trendithon.SpinOff.domain.member.repository;

import Trendithon.SpinOff.domain.member.entity.Technic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface TechnicJpaRepository extends JpaRepository<Technic, Long> {
    Optional<Technic> findByTechnicName(String technicName);
}
