package Trendithon.SpinOff.domain.boardlike.service;

import Trendithon.SpinOff.domain.boardlike.dto.HeartRequestDto;
import Trendithon.SpinOff.domain.board.entity.Board;
import Trendithon.SpinOff.domain.boardlike.entity.HeartBoard;
import Trendithon.SpinOff.domain.board.repository.BoardRepository;
import Trendithon.SpinOff.domain.boardlike.repository.HeartRepository;
import Trendithon.SpinOff.domain.member.entity.Member;
import Trendithon.SpinOff.domain.member.repository.MemberJpaRepository;

import Trendithon.SpinOff.domain.profile.valid.exception.MemberNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class HeartService {
    private final HeartRepository heartRepository;
    private final MemberJpaRepository memberRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public void insert(HeartRequestDto heartRequestDTO, Long boardId) throws Exception {
        Member member = memberRepository.findById(heartRequestDTO.getMemberId())
                .orElseThrow(() -> new MemberNotFoundException("Could not found member id : " + heartRequestDTO.getMemberId()));

        Board board = boardRepository.findById(heartRequestDTO.getBoardId())
                .orElseThrow(() -> new MemberNotFoundException("Could not found board id : " + heartRequestDTO.getBoardId()));
        // 이미 좋아요 되어 있음
        if (heartRepository.findByMemberAndBoard(member, board).isPresent()) {
            //에러
            throw new Exception();
        }

        HeartBoard heart = HeartBoard.builder()
                .board(board)
                .member(member)
                .build();

        heartRepository.save(heart);

        // 좋아요 누를 시 게시물의 좋아요 증가
        board.setBoard_like(board.getBoard_like() + 1);
        boardRepository.save(board);
    }

    @Transactional
    public void delete(HeartRequestDto heartRequestDTO, Long boardId) {

        Member member = memberRepository.findById(heartRequestDTO.getMemberId())
                .orElseThrow(() -> new MemberNotFoundException("Could not found member id : " + heartRequestDTO.getMemberId()));

        Board board = boardRepository.findById(heartRequestDTO.getBoardId())
                .orElseThrow(() -> new MemberNotFoundException("Could not found board id : " + heartRequestDTO.getBoardId()));

        HeartBoard heart = heartRepository.findByMemberAndBoard(member, board)
                .orElseThrow(() -> new MemberNotFoundException("Could not found heart id"));

        heartRepository.delete(heart);

        board.setBoard_like(board.getBoard_like() - 1);
        boardRepository.save(board);

    }


    public Member getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null; // 인증안되었을 때
        }
        return (Member) authentication.getPrincipal();
    }
}
