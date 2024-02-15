package Trendithon.SpinOff.domain.member.controller;

import Trendithon.SpinOff.domain.member.dto.EditInformation;
import Trendithon.SpinOff.domain.member.dto.Information;
import Trendithon.SpinOff.domain.member.dto.ProfileInformation;
import Trendithon.SpinOff.domain.member.service.ProfileService;
import Trendithon.SpinOff.domain.member.valid.exception.CheckMemberIdNotNullException;
import Trendithon.SpinOff.domain.member.valid.exception.IntroduceOutOfBoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/api")
@AllArgsConstructor
public class ProfileController {
    private final ProfileService profileService;

    @PostMapping("/information/add")
    public ResponseEntity<Boolean> addInformation(@Valid @RequestBody Information information, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
           throw new IntroduceOutOfBoundException("한 줄 소개의 길이가 22자를 넘었습니다.");
        }
        log.info("멤버 아이디 = {}", information.getMemberId());
        boolean resultInfo = profileService.addInformation(information);
        boolean resultTechnic = profileService.addTechnic(information.getMemberId(), information.getTechnics());
        return ResponseEntity.ok(resultInfo && resultTechnic);
    }

    @PostMapping("/information/edit")
    public ResponseEntity<Boolean> editInformation(@Valid @RequestBody EditInformation editInformation, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new IntroduceOutOfBoundException("한 줄 소개의 길이가 22자를 넘었습니다.");
        }
        boolean resultInfo = profileService.editInformation(editInformation);
        boolean resultTechnic = profileService.editTechnics(editInformation.getMemberId(), editInformation.getTechnics());
        return ResponseEntity.ok(resultInfo && resultTechnic);
    }

    @GetMapping("/information/check/{memberId}")
    public ResponseEntity<ProfileInformation> checkInformation(@PathVariable String memberId) {
        log.info("memberId = {}", memberId);
        return ResponseEntity.ok(profileService.checkInformation(memberId));
    }
}
