package Trendithon.SpinOff.domain.notify.service;

import Trendithon.SpinOff.domain.board.entity.Board;
import Trendithon.SpinOff.domain.board.repository.BoardRepository;
import Trendithon.SpinOff.domain.member.entity.Member;
import Trendithon.SpinOff.domain.notify.domain.dto.LikeMessage;
import Trendithon.SpinOff.domain.profile.entity.Profile;
import Trendithon.SpinOff.domain.member.repository.MemberJpaRepository;
import Trendithon.SpinOff.domain.profile.repository.ProfileJpaRepository;
import Trendithon.SpinOff.domain.profile.valid.exception.MemberNotFoundException;
import Trendithon.SpinOff.domain.profile.valid.exception.ProfileNotFoundException;
import Trendithon.SpinOff.domain.notify.repository.EmitterRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private static final Long DEFAULT_TIMEOUT = 600L * 1000 * 60;

    private final MemberJpaRepository memberJpaRepository;
    private final EmitterRepository emitterRepository;
    private final ProfileJpaRepository profileJpaRepository;
    private final BoardRepository boardRepository;

    public SseEmitter subscribe(String memberId) {
        Optional<Member> optionalMember = memberJpaRepository.findByMemberId(memberId);
        if (optionalMember.isPresent()) {
            Member member = optionalMember.get();
            Long id = member.getId();
            SseEmitter emitter = createEmitter(id);
            sendToClient(id, "EventStream Created. [memberId= " + id + "]", "sse 접속 성공", "sse");
            return emitter;
        } else {
            throw new MemberNotFoundException("Member with memberId " + memberId + " not found");
        }
    }

    public <T> void notify(Long memberId, T data, String comment, String type) {
        sendToClient(memberId, data, comment, type);
    }

    public Member validUser(String memberId) {
        return memberJpaRepository.findByMemberId(memberId)
                .orElseThrow(() -> new MemberNotFoundException("Member with memberId " + memberId + " not found"));
    }

    public Profile validProfile(Long profileId) {
        return profileJpaRepository.findById(profileId)
                .orElseThrow(() -> new ProfileNotFoundException("Profile with profileId " + profileId + " not found"));
    }

    private <T> void sendToClient(Long memberId, T data, String comment, String type) {
        SseEmitter emitter = emitterRepository.get(memberId);
        if (emitter != null) {
            try {
                emitter.send(SseEmitter.event()
                        .id(String.valueOf(memberId))
                        .data(data)
                        .name(type)
                        .comment(comment)
                );
            } catch (IOException e) {
                emitterRepository.deleteById(memberId);
                emitter.completeWithError(e);
            }
        }
    }

    private SseEmitter createEmitter(Long memberId) {
        SseEmitter emitter = new SseEmitter(DEFAULT_TIMEOUT);
        emitterRepository.save(memberId, emitter);

        emitter.onCompletion(() -> emitterRepository.deleteById(memberId));
        emitter.onTimeout(() -> emitterRepository.deleteById(memberId));

        return emitter;
    }

    @Transactional
    public void doLike(String liker, Long projectId) {
        Optional<Board> optionalBoard = boardRepository.findById(projectId);
        if (optionalBoard.isPresent()) {
            Board board = optionalBoard.get();
            Member member = validUser(board.getWriter());
            String imageUrl = member.getProfile().getImageUrl();
            String comment = liker + "가 " + board.getProjectName() + "을 좋아합니다.";
            LikeMessage likeMessage = new LikeMessage(member.getMemberId(), comment, board.getProjectName(), imageUrl);
            notify(member.getId(), likeMessage, comment, "like");
        }
    }
}
