package Trendithon.SpinOff.domain.board.service;

import Trendithon.SpinOff.domain.board.dto.BoardDto;
import Trendithon.SpinOff.domain.board.dto.BoardResponseDto;
import Trendithon.SpinOff.domain.board.entity.Board;
import Trendithon.SpinOff.domain.board.repository.BoardRepository;
import Trendithon.SpinOff.domain.member.repository.MemberJpaRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final MemberJpaRepository memberJpaRepository;

    @Transactional
    public void save(String boardDTO) throws JsonProcessingException {

        System.out.println(boardDTO);
        ObjectMapper objectMapper = new ObjectMapper();
        BoardDto boardDTO1 = objectMapper.readValue(boardDTO, BoardDto.class);
        Board board = new Board();
        board.setBoard_title(boardDTO1.getBoardTitle());
        board.setBoard_context(boardDTO1.getBoardContext());
        board.setBoard_like(boardDTO1.getBoardLike());

        ObjectMapper objectMapper1 = new ObjectMapper();
        String jsonString = objectMapper1.writeValueAsString(boardDTO1.getImageUrl());
        board.setImage_url(jsonString);

        boardRepository.save(board);
    }

    public void saveUpdate(String boardDTO) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        BoardDto boardDTO1 = objectMapper.readValue(boardDTO, BoardDto.class);
        Board board = new Board();
        board.setBoard_id(boardDTO1.getBoardId());
        board.setBoard_title(boardDTO1.getBoardTitle());
        board.setBoard_context(boardDTO1.getBoardContext());
        board.setBoard_like(boardDTO1.getBoardLike());

        ObjectMapper objectMapper1 = new ObjectMapper();
        String jsonString = objectMapper1.writeValueAsString(boardDTO1.getImageUrl());
        board.setImage_url(jsonString);

        boardRepository.save(board);

    }

    @Transactional
    public List<BoardResponseDto> search(String title) throws JsonProcessingException {
        List<Board> boardEntities = boardRepository.findWithParams(title);
        List<BoardResponseDto> boardResponseDtoList = new ArrayList<>();
        for (Board board : boardEntities) {
            boardResponseDtoList.add(BoardResponseDto.toDTO(board));
        }
        return boardResponseDtoList;
    }


    @Transactional
    public BoardResponseDto findByBoardId(Long boardId) throws JsonProcessingException {
        Optional<Board> boardEntityOptional = boardRepository.findById(boardId);
        if (boardEntityOptional.isPresent()) {
            Board board = boardEntityOptional.get();
            BoardResponseDto boardResponseDTO = BoardResponseDto.toDTO(boardEntityOptional.get());
            //boardResponseDTO.setProfileImage(board.getMember().getProfile());
            return boardResponseDTO;
        } else {
            return null; // 또는 다른 상태 코드를 사용
        }
    }

    public void delete(Long boardId) {
        boardRepository.deleteById(boardId);
    }
}

