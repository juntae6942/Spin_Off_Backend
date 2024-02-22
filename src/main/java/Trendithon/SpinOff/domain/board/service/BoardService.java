package Trendithon.SpinOff.domain.board.service;

import Trendithon.SpinOff.domain.board.dto.BoardDto;
import Trendithon.SpinOff.domain.board.dto.BoardResponseDto;
import Trendithon.SpinOff.domain.board.entity.Board;
import Trendithon.SpinOff.domain.board.repository.BoardPopularPostRepository;
import Trendithon.SpinOff.domain.board.repository.BoardRepository;
import Trendithon.SpinOff.domain.board.repository.BoardSearchRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final BoardSearchRepository boardSearchRepository;
    private final BoardPopularPostRepository boardPopularPostRepository;
    @Transactional
    public void save(BoardDto boardDTO) throws JsonProcessingException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserEmail = authentication.getName(); // 현재 사용자의 이메일 가져오

        Board board = new Board();
        System.out.println(board);
        board.setProjectName(boardDTO.getProjectName());
        board.setProjectDescription(boardDTO.getProjectDescription());
        board.setProjectBackground(boardDTO.getProjectBackground());
        board.setCategory(boardDTO.getCategory());
        board.setGithub(boardDTO.getGithub());
        board.setProjectFeatures(boardDTO.getProjectFeatures());
        board.setDistribution(boardDTO.getDistribution());
        board.setBoardLike(0);
        board.setWriter(currentUserEmail);
        board.setProjectImage(boardDTO.getProjectImage());
        boardRepository.save(board);
    }

    public Page<Board> boardList(Pageable pageable) {
        //findAll : 테스트보드라는 클래스가 담긴 List를 반환하는것을 확인할수있다
        return boardRepository.findAll(pageable);
    }

    public Page<Board> boardPopularList(Pageable pageable) {
        return boardPopularPostRepository.findByBoardPopularPost(pageable);
    }

    public Page<Board> boardSearchList(String searchKeyword, Pageable pageable) {
        return boardSearchRepository.findByBoardTitleContaining(searchKeyword, pageable);
    }

    public void saveUpdate(String boardDTO, Long boardId) throws JsonProcessingException {
        // 기존 게시물을 확인하여 존재하는지 확인
        Board existingBoard = boardRepository.findById(boardId)
                .orElseThrow(() -> new EntityNotFoundException("해당 ID의 게시물을 찾을 수 없습니다."));

        // ObjectMapper를 사용하여 JSON 문자열을 BoardDto 객체로 변환
        ObjectMapper objectMapper = new ObjectMapper();
        BoardDto updatedBoardDto = objectMapper.readValue(boardDTO, BoardDto.class);

        // 기존 게시물의 필드들을 업데이트
        existingBoard.setProjectName(updatedBoardDto.getProjectName());
        existingBoard.setProjectDescription(updatedBoardDto.getProjectDescription());
        existingBoard.setProjectBackground(updatedBoardDto.getProjectBackground());
        existingBoard.setProjectFeatures(updatedBoardDto.getProjectFeatures());
        BoardDto boardDTO1 = objectMapper.readValue(boardDTO, BoardDto.class);
        Board board=new Board();
        board.setGithub(boardDTO1.getGithub());
        // 필요한 필드들을 추가적으로 업데이트해 나갈 수 있습니다.

        // 기존 게시물 엔터티를 저장
        boardRepository.save(existingBoard);
    }

    @Transactional
    public List<BoardResponseDto> search(String projectName) throws JsonProcessingException {
        List<Board> boardEntities = boardRepository.findWithParams(projectName);
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

