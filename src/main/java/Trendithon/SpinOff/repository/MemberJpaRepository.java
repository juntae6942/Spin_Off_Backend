package Trendithon.SpinOff.repository;

import Trendithon.SpinOff.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberJpaRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);
    Optional<Member> findMemberById(Long id);
    Optional<Member> findMemberByName(String name);
    Optional<Member> findByMemberId(String memberId);
    Optional<Member> findOneWithAuthorityByMemberId(String memberId);
}
