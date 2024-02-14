package Trendithon.SpinOff.domain.member.controller;

import Trendithon.SpinOff.domain.member.dto.EditInformation;
import Trendithon.SpinOff.domain.member.dto.Information;
import Trendithon.SpinOff.domain.member.dto.ProfileInformation;
import Trendithon.SpinOff.domain.member.service.ProfileService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/api")
@AllArgsConstructor
public class ProfileController {
    private final ProfileService profileService;

    @PostMapping("/information/add")
    public ResponseEntity<Boolean> addInformation(@RequestBody Information information) {
        log.info("멤버 아이디 = {}", information.getMemberId());
        boolean resultInfo = profileService.addInformation(information);
        boolean resultTechnic = profileService.addTechnic(information.getMemberId(), information.getTechnics());
        return ResponseEntity.ok(resultInfo && resultTechnic);
    }

    @PostMapping("/information/edit")
    public ResponseEntity<Boolean> editInformation(@RequestBody EditInformation editInformation) {
        boolean resultInfo = profileService.editInformation(editInformation);
        boolean resultTechnic = profileService.editTechnics(editInformation.getMemberId(), editInformation.getTechnics());
        return ResponseEntity.ok(resultInfo && resultTechnic);
    }

    @GetMapping("/information/check")
    public ResponseEntity<ProfileInformation> checkInformation(@RequestParam String memberId) {
        log.info("memberId = {}", memberId);
        return ResponseEntity.ok(profileService.checkInformation(memberId));
    }
}
