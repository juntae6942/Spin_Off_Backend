package Trendithon.SpinOff.domain.member.repository;

import Trendithon.SpinOff.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface MemberJpaRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);
    Optional<Member> findMemberById(Long id);
    Optional<Member> findMemberByName(String name);
    Optional<Member> findByMemberId(String memberId);
    Optional<Member> findOneWithAuthorityByMemberId(String memberId);
}
