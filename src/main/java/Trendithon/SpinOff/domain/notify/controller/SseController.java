package Trendithon.SpinOff.domain.notify.controller;

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

    @PostMapping(value = "/like", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<Void> like(@RequestBody LikeMessage likeMessage) {
        notificationService.doLike(likeMessage.getLiker());
        return ResponseEntity.ok().build();
    }
}