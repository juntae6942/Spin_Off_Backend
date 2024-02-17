package Trendithon.SpinOff.domain.heart.controller;

import Trendithon.SpinOff.domain.heart.dto.HeartRequestDto;
import Trendithon.SpinOff.domain.heart.service.HeartService;
import Trendithon.SpinOff.global.entity.ResponseResult;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class HeartController {

    private final HeartService heartService;

    @PutMapping("/{boardId}/like")
    public ResponseEntity<ResponseResult<?>> insert(@RequestBody @Valid HeartRequestDto heartRequestDTO,
                                                    @PathVariable Long boardId) {
        try {
            heartService.insert(heartRequestDTO, boardId);
            return ResponseEntity.ok(ResponseResult.success("좋아요 추가 성공", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ResponseResult.failure("이미 좋아요 누름"));
        }
    }

    @DeleteMapping("/{boardId}/dellike")
    public ResponseEntity<ResponseResult<?>> delete(@RequestBody @Valid HeartRequestDto heartRequestDTO,
                                                    @PathVariable Long boardId) {
        try {
            heartService.delete(heartRequestDTO, boardId);
            return ResponseEntity.ok(ResponseResult.success("좋아요 삭제 성공", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ResponseResult.failure("좋아요 안 눌렀넹?"));
        }

    }
}