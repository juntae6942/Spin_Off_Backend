package Trendithon.SpinOff.domain.notify.controller;

import Trendithon.SpinOff.domain.heart.service.HeartService;
import Trendithon.SpinOff.domain.notify.domain.dto.LikeMessage;
import Trendithon.SpinOff.domain.notify.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SseController {
    private final NotificationService notificationService;

    @GetMapping(value = "/connect/{memberId}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<SseEmitter> connect(@PathVariable String memberId) {
        return ResponseEntity.ok().body(notificationService.subscribe(memberId));
    }

    @PostMapping(value = "/project/liked", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<Void> likeProjectAlarm(@RequestBody LikeMessage likeMessage) {
        String liker = likeMessage.liker();
        Long projectId = likeMessage.projectId();
        notificationService.doLike(liker, projectId);
        return ResponseEntity.ok().build();
    }
}