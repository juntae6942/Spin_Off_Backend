package Trendithon.SpinOff.domain.board.controller;


import Trendithon.SpinOff.domain.board.dto.BoardDto;
import Trendithon.SpinOff.domain.board.dto.BoardResponseDto;
import Trendithon.SpinOff.domain.board.entity.Board;
import Trendithon.SpinOff.domain.board.service.BoardService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.persistence.EntityNotFoundException;
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
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BoardController {

    private final BoardService boardService;


    @PostMapping(value = "/write")
    @Operation(summary = "글 생성")
    public ResponseEntity<String> save(@RequestBody BoardDto boardDto) throws JsonProcessingException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserEmail = authentication.getName(); // 현재 사용자의 이메일 가져오기
        System.out.println(authentication);
        System.out.println("currentUserEmail"+currentUserEmail);
        // 현재 사용자를 찾을 수 없는 경우 Forbidden 반환
        if (currentUserEmail   == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("사용자를 찾을 수 없습니다.");
        }

        boardService.save(boardDto);

        return ResponseEntity.ok("저장 성공");
    }
    @GetMapping("/popular/list")
    @Operation(summary = "좋아요 기준 인기 게시물")
    public ResponseEntity<List<BoardResponseDto>> boardpopularList(
            @PageableDefault(page = 0, size = 7, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {

        Page<BoardResponseDto> popularBoardResponsePage = boardService.boardPopularList(pageable).map(board ->{
            try {
                return BoardResponseDto.toDTO(board);
            } catch (JsonProcessingException e) {
                // 예외 처리
                return null; // 예외가 발생하면 null을 반환하거나 다른 처리를 수행할 수 있습니다.
            }
        });

        List<BoardResponseDto> popularBoardResponseList = popularBoardResponsePage.getContent();
        return ResponseEntity.ok().body(popularBoardResponseList);
    }


    @GetMapping("/search/list")
    @Operation(summary = "검색 결과 게시물")
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

    @GetMapping("/search/all")
    @Operation(summary = "전체 조회")
    public ResponseEntity<List<BoardResponseDto>> search(@RequestParam(required = false) String projectName)
            throws JsonProcessingException {
        List<BoardResponseDto> boardResponseDtoList = boardService.search(projectName);
        return new ResponseEntity<>(boardResponseDtoList, HttpStatus.OK);
    }

    @PutMapping("/update/{boardId}")
    @Operation(summary = "게시물 수정")
    public ResponseEntity<String> update(@RequestBody String boardData, @PathVariable Long boardId) throws JsonProcessingException {
        try {
            boardService.saveUpdate(boardData, boardId);
            return ResponseEntity.ok("수정 성공");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{boardId}")
    @Operation(summary = "게시물 삭제")
    public ResponseEntity<String> delete(@PathVariable String boardId) {
        boardService.delete(Long.parseLong(boardId));
        return ResponseEntity.ok("삭제 성공");
    }

    @GetMapping("/{boardId}")
    @Operation(summary = "게시불id 기준으로 가져오기")
    public ResponseEntity<BoardResponseDto> showDetail(@PathVariable String boardId) throws JsonProcessingException {
        BoardResponseDto boardResponseDTO = boardService.findByBoardId(Long.parseLong(boardId));
        return new ResponseEntity<>(boardResponseDTO, HttpStatus.OK);
    }
}