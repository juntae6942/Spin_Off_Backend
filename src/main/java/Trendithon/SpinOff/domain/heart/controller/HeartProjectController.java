package Trendithon.SpinOff.domain.heart.controller;

import Trendithon.SpinOff.domain.board.entity.Board;
import Trendithon.SpinOff.domain.board.dto.BoardResponseDto;
import Trendithon.SpinOff.domain.heart.entity.HeartProject;
import Trendithon.SpinOff.domain.heart.repository.HeartProjectRepository;
import Trendithon.SpinOff.domain.heart.service.HeartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class HeartProjectController {

    private final HeartProjectRepository heartProjectRepository;
    private final HeartService heartService;

    @Autowired
    public HeartProjectController(HeartProjectRepository heartProjectRepository, HeartService heartService) {
        this.heartProjectRepository = heartProjectRepository;
        this.heartService = heartService;
    }

    @GetMapping("/likedlist/{memberName}")
    public ResponseEntity<BoardResponseDto> showLikedList(@PathVariable String memberName) {
        List<HeartProject> heartProjects = heartProjectRepository.findByMemberName(memberName);
        if (!heartProjects.isEmpty()) {
            HeartProject heartProject = heartProjects.get(0); // 첫 번째 항목을 가져옴
            Board board = heartProject.getBoard();
            BoardResponseDto responseDto = new BoardResponseDto();
            responseDto.setBno(board.getBno());
            responseDto.setProjectName(board.getProjectName());
            responseDto.setProjectDescription(board.getProjectDescription());
            responseDto.setProjectBackground(board.getProjectBackground());
            responseDto.setProjectFeatures(board.getProjectFeatures());
            responseDto.setProjectImage(board.getProjectImage());
            responseDto.setGithub(board.getGithub());
            responseDto.setMember1(board.getMember1());
            responseDto.setMember2(board.getMember2());
            responseDto.setMember3(board.getMember3());
            responseDto.setMember4(board.getMember4());
            responseDto.setMember5(board.getMember5());
            responseDto.setCategory(board.getCategory());
            responseDto.setBoardLike(board.getBoardLike());
            responseDto.setWriter(board.getWriter());
            // 나머지 필드들도 위와 같이 설정해주어야 합니다.

            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/check/like/my")
    public ResponseEntity<List<Board>> findAllLikedProject() {
        String memberId = SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.ok(heartService.findAllByMemberName(memberId));
    }
}
