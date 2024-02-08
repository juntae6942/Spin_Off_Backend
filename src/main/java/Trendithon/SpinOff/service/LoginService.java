package Trendithon.SpinOff.service;

import Trendithon.SpinOff.domain.member.Member;
import Trendithon.SpinOff.repository.MemberJpaRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class LoginService implements UserDetailsService {
    private final MemberJpaRepository memberJpaRepository;

    @Autowired
    public LoginService(MemberJpaRepository memberJpaRepository) {
        this.memberJpaRepository = memberJpaRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return memberJpaRepository.findOneWithAuthorityByEmail(email)
                .map(member -> createMember(email, member))
                .orElseThrow(()->new UsernameNotFoundException(email + " -> 데이터베이스에서 찾을 수 없습니다."));
    }

    private User createMember(String memberName, Member member) {
        if (!member.isActivated()) {
            throw new RuntimeException(memberName + " -> 활성화되어 있지 않습니다.");
        }
        List<GrantedAuthority> grantedAuthorities = Collections.singletonList(new SimpleGrantedAuthority(member.getAuthority().getAuthority()));

        return new User(member.getEmail(), member.getPassword(), grantedAuthorities);
    }

    @Transactional
    public Optional<Member> getMemberWithAuthorities(String email) {
        return memberJpaRepository.findOneWithAuthorityByEmail(email);
    }

    /*@Transactional
    public Optional<Member> getMyMemberWithAuthorities() {
        return SecurityUtil.getCurrentUsername().flatMap(memberJpaRepository::findOneWithAuthorityByEmail);
    }*/
}
