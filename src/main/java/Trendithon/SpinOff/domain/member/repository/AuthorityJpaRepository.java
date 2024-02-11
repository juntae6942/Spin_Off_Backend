package Trendithon.SpinOff.domain.member.repository;

import Trendithon.SpinOff.global.jwt.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorityJpaRepository extends JpaRepository<Authority, Long> {
    Optional<Authority> findByAuthority(String auth);
}
