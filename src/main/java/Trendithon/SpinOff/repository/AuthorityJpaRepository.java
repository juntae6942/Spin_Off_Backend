package Trendithon.SpinOff.repository;

import Trendithon.SpinOff.domain.member.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorityJpaRepository extends JpaRepository<Authority, Long> {
    Optional<Authority> findByAuthority(String auth);
}
