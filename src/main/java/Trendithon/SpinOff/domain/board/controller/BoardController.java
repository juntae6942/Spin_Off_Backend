package Trendithon.SpinOff.domain.board.controller;


import Trendithon.SpinOff.domain.board.dto.BoardDto;
import Trendithon.SpinOff.domain.board.dto.BoardResponseDto;
import Trendithon.SpinOff.domain.board.service.BoardService;
import Trendithon.SpinOff.domain.member.entity.Member;
import Trendithon.SpinOff.domain.member.service.MemberService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/project")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final MemberService memberService;
    @PostMapping(value = "/write")
    public ResponseEntity<String> save(@RequestBody BoardDto boardDto) throws JsonProcessingException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserEmail = authentication.getName(); // 현재 사용자의 이메일 가져오기
        Member currentMember = memberService.findByEmail(currentUserEmail);
        System.out.println("currentUserEmail"+currentUserEmail);


        // 현재 사용자를 찾을 수 없는 경우 Forbidden 반환
        if (currentUserEmail   == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("사용자를 찾을 수 없습니다.");
        }



        // 작성자 이름 설정
        //Long writerId = currentMember.getId();
        //System.out.println(currentMember+"현재 맴버");
        //System.out.println(currentMember.getMemberId()+"멤버아이디");
        //boardDto.setWriter_id(writerId);
        // BoardDto를 서비스로 전달
        boardService.save(boardDto);

        return ResponseEntity.ok("저장 성공");
    }
    @GetMapping("/board/list")
    public ResponseEntity<Page<BoardResponseDto>> boardList(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                                                            @RequestParam(required = false) String searchKeyword) {
        Page<BoardResponseDto> list;
        if (searchKeyword == null) {
            list = boardService.boardList(pageable).map(board -> {
                try {
                    return BoardResponseDto.toDTO(board);
                } catch (JsonProcessingException e) {
                    // 예외 처리
                    e.printStackTrace();
                    return null; // 예외가 발생하면 null을 반환하거나 다른 처리를 수행할 수 있습니다.
                }
            });
        } else {
            list = boardService.boardSearchList(searchKeyword, pageable).map(board -> {
                try {
                    return BoardResponseDto.toDTO(board);
                } catch (JsonProcessingException e) {
                    // 예외 처리
                    e.printStackTrace();
                    return null; // 예외가 발생하면 null을 반환하거나 다른 처리를 수행할 수 있습니다.
                }
            });
        }
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/search")
    public ResponseEntity<List<BoardResponseDto>> search(@RequestParam(required = false) String title)
            throws JsonProcessingException {
        List<BoardResponseDto> boardResponseDtoList = boardService.search(title);
        return new ResponseEntity<>(boardResponseDtoList, HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<String> update(@RequestBody String boardData) throws JsonProcessingException {
        //System.out.println(boardData);
        boardService.saveUpdate(boardData);
        return ResponseEntity.ok("수정 성공");
    }

    @DeleteMapping("/delete/{boardId}")
    public ResponseEntity<String> delete(@PathVariable String boardId) {
        boardService.delete(Long.parseLong(boardId));
        return ResponseEntity.ok("삭제 성공");
    }

    @GetMapping("/{boardId}")
    public ResponseEntity<BoardResponseDto> showDetail(@PathVariable String boardId) throws JsonProcessingException {
        BoardResponseDto boardResponseDTO = boardService.findByBoardId(Long.parseLong(boardId));
        return new ResponseEntity<>(boardResponseDTO, HttpStatus.OK);
    }
}
