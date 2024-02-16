package Trendithon.SpinOff.domain.board.controller;


import Trendithon.SpinOff.domain.board.dto.BoardResponseDto;
import Trendithon.SpinOff.domain.board.service.BoardService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/project")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping(value = "/write")
    public ResponseEntity<String> save(@RequestBody String boardDto) throws JsonProcessingException {

        boardService.save(boardDto);
        return ResponseEntity.ok("저장 성공");
    }

    @GetMapping("/search")
    public ResponseEntity<List<BoardResponseDto>> search(@RequestParam(required = false) String title)throws JsonProcessingException {
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
    public ResponseEntity<String> delete(@PathVariable String boardId){
        boardService.delete(Long.parseLong(boardId));
        return ResponseEntity.ok("삭제 성공");
    }

    @GetMapping("/{boardId}")
    public ResponseEntity<BoardResponseDto> showDetail(@PathVariable String boardId) throws JsonProcessingException {
        BoardResponseDto boardResponseDTO = boardService.findByBoardId(Long.parseLong(boardId));
        return new ResponseEntity<>(boardResponseDTO, HttpStatus.OK);
    }

}
