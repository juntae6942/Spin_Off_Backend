package Trendithon.SpinOff.domain.heart.controller;

import Trendithon.SpinOff.domain.heart.dto.HeartJobPostingDto;
import Trendithon.SpinOff.domain.heart.dto.HeartProjectDto;
import Trendithon.SpinOff.domain.heart.service.HeartService;
import Trendithon.SpinOff.global.entity.ResponseResult;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class HeartController {
    private final HeartService heartService;

    @PutMapping("/board/like")
    @Operation(summary = "좋아요 추가")
    public ResponseEntity<ResponseResult<?>> insert(@RequestBody @Valid HeartProjectDto heartProjectDto) {
        try {
            heartService.insertProject(heartProjectDto.getMemberId(), heartProjectDto.getBno());
            return ResponseEntity.ok(ResponseResult.success("좋아요 추가 성공", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ResponseResult.failure("이미 좋아요 누름"));
        }
    }

    @DeleteMapping("/board/unlike")
    @Operation(summary = "좋아요 취소")
    public ResponseEntity<ResponseResult<?>> delete(@RequestBody @Valid HeartProjectDto heartProjectDto) {
        try {
            heartService.deleteProject(heartProjectDto.getMemberId(), heartProjectDto.getBno());
            return ResponseEntity.ok(ResponseResult.success("좋아요 삭제 성공", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ResponseResult.failure("좋아요 안 눌렀넹?"));
        }
    }

    @PutMapping("/job/like")
    public ResponseEntity<ResponseResult<?>> insert(@RequestBody @Valid HeartJobPostingDto heartJobPostingDto) {
        try {
            heartService.insertJobPosting(heartJobPostingDto.memberId(), heartJobPostingDto.jobPostingId());
            return ResponseEntity.ok(ResponseResult.success("좋아요 추가 성공", null));
        } catch (Exception e) {
            log.info("error message = {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ResponseResult.failure("이미 좋아요를 눌렀습니다."));
        }
    }

    @DeleteMapping("/job/unlike")
    public ResponseEntity<ResponseResult<?>> delete(@RequestBody @Valid HeartJobPostingDto heartJobPostingDto) {
        try {
            heartService.deleteJobPosting(heartJobPostingDto.memberId(), heartJobPostingDto.jobPostingId());
            return ResponseEntity.ok(ResponseResult.success("좋아요 삭제 성공", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ResponseResult.failure("해당 공고에 좋아요를 누르지 않았습니다."));
        }
    }
}