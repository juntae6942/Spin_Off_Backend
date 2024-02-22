package Trendithon.SpinOff.domain.notify.controller;

import Trendithon.SpinOff.domain.notify.domain.dto.LikerInfo;
import Trendithon.SpinOff.domain.notify.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Slf4j
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class SseController {
    private final NotificationService notificationService;

    @GetMapping(value = "/alarm/connect", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<SseEmitter> connect() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok().body(notificationService.subscribe(authentication.getName()));
    }

    @PostMapping(value = "/project/liked", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<Void> likeProjectAlarm(@RequestBody LikerInfo likeMessage) {
        String liker = likeMessage.liker();
        Long projectId = likeMessage.projectId();
        notificationService.doLike(liker, projectId);
        return ResponseEntity.ok().build();
    }
}