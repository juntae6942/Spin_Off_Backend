package Trendithon.SpinOff.web;

import Trendithon.SpinOff.domain.Member;
import Trendithon.SpinOff.service.MemberService;
import Trendithon.SpinOff.service.TokenService;
import Trendithon.SpinOff.domain.dto.LoginDto;
import Trendithon.SpinOff.domain.dto.SignUpDto;
import Trendithon.SpinOff.domain.dto.TokenDto;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.Optional;

@Slf4j
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api")
public class MemberController {
    private final MemberService memberService;
    private final TokenService tokenService;

    @Autowired
    public MemberController(MemberService memberService, TokenService tokenService) {
        this.memberService = memberService;
        this.tokenService = tokenService;
    }
    
    @PostMapping("/sign-up")
    public ResponseEntity<Boolean> signUp(@Validated @RequestBody SignUpDto signUpDto, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            log.info("아이디 혹은 비밀번호를 잘못입력했습니다.");
        }
        return memberService.signUp(signUpDto);
    }   // 회원가입

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@Valid @RequestBody LoginDto loginDto) {
        return tokenService.makeToken(loginDto);
    } // 회원 로그인


    @PostMapping("/checkDuplicateMemberId")
    public ResponseEntity<Boolean> checkDuplicate(@RequestBody HashMap<String,String> member) {
        String memberId = member.get("memberId");
        Optional<Member> byMemberId = memberService.findByEmail(memberId);

        log.info(memberId);

        if(byMemberId.isEmpty()){
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.ok(false);
        }
    }   // 회원 아이디 중복 검사
}
